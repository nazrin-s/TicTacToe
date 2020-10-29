import java.io.*;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
public class TicTacToe{

    public static void main(String[] args) throws IOException{
        
        ArrayList<Integer> playerpositions = new ArrayList<Integer>();
        ArrayList<Integer> cpupositions = new ArrayList<Integer>();
        InputStreamReader is=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(is);
        char[][] gameboard = {{' ','|',' ','|',' '}, 
                              {'-','+','-','+','-'}, 
                              {' ','|',' ','|',' '}, 
                              {'-','+','-','+','-'}, 
                              {' ','|',' ','|',' '}
                             };
        printboard(gameboard);
        while(true){
            System.out.print("Enter your placements (1-9) : ");
            int plpos = Integer.parseInt(br.readLine());
            while(playerpositions.contains(plpos)||cpupositions.contains(plpos)){
                System.out.print("Position taken.Enter a valid position:");
                plpos= Integer.parseInt(br.readLine());
            }
            placeonboard(gameboard,plpos,"player",playerpositions,cpupositions);
            String res = checkWinner(playerpositions,cpupositions);
            if(res.length()>0){
                System.out.println(res);
                break;
            }

            Random rand=new Random();
            int cpupos= rand.nextInt(9)+1;
            while(playerpositions.contains(cpupos)||cpupositions.contains(cpupos)){
                cpupos= rand.nextInt(9)+1;
            }

            placeonboard(gameboard,cpupos,"cpu",playerpositions,cpupositions);
            printboard(gameboard);
    }
    }
    public static void printboard(char[][] gameboard){
        for(char[] row : gameboard){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }
    public static void placeonboard(char[][] gameboard,int pos,String user,ArrayList<Integer> playerpositions,ArrayList<Integer> cpupositions){
        
        char symbol=' ';
        if(user.equals("player")){
            symbol='X';
            playerpositions.add(pos);
        }
        else if(user.equals("cpu")){
            symbol='O';
            cpupositions.add(pos);
        }
        switch(pos){
            case 1: gameboard[0][0]=symbol;
                    break;
            case 2: gameboard[0][2]=symbol;
                    break;
            case 3: gameboard[0][4]=symbol;
                    break;
            case 4: gameboard[2][0]=symbol;
                    break;
            case 5: gameboard[2][2]=symbol;
                    break;
            case 6: gameboard[2][4]=symbol;
                    break; 
            case 7: gameboard[4][0]=symbol;
                    break;
            case 8: gameboard[4][2]=symbol;
                    break;
            case 9: gameboard[4][4]=symbol;
                    break;
            default:break;
        }

    }
    public static String checkWinner(ArrayList<Integer> playerpositions,ArrayList<Integer> cpupositions){
        List toprow=Arrays.asList(1,2,3);
        List midrow=Arrays.asList(4,5,6);
        List bottomrow=Arrays.asList(7,8,9);
        List leftcol=Arrays.asList(1,4,7);
        List midcol=Arrays.asList(2,5,8);
        List rightcol=Arrays.asList(3,6,9);
        List cross1=Arrays.asList(1,5,9);
        List cross2=Arrays.asList(7,5,3);
        List<List> wincond = new ArrayList<List>();
        wincond.add(toprow);
        wincond.add(midrow);
        wincond.add(bottomrow);
        wincond.add(leftcol);
        wincond.add(midcol);
        wincond.add(rightcol);
        wincond.add(cross1);
        wincond.add(cross2);

        for( List l : wincond){
            if(playerpositions.containsAll(l)){
                return "Congratulations you won!";
            }
            else if(cpupositions.containsAll(l)){
                return "CPU wins!!";
            }
            else if(playerpositions.size()+cpupositions.size()==9){
                return "TIE!!";
            }
        }
        return "";
    
    }
}