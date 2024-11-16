package ttfe;


public class ComputerPlayer implements PlayerInterface{
    
    @Override
    public MoveDirection getPlayerMove(SimulatorInterface game, UserInterface ui) {
        MoveDirection dir = null;
       
        
        if(game.isMovePossible()) {
            dir = MoveDirection.SOUTH;
            game.performMove(dir);
        }
        if(game.isMovePossible()) {
            dir = MoveDirection.WEST;
            game.performMove(dir);
        } else {
            dir = MoveDirection.NORTH;
            game.performMove(dir);
        }
        
        return dir;
    
    
        
    }

   
    
}
