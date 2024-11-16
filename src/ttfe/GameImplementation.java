package ttfe;

import java.util.Random;

public class GameImplementation implements SimulatorInterface {
    private int[][] gameBoard;
    private int w;
    private int h;
    private int pieces;
    private int moves;
    private int points;
    private Random random;
    //private int mergeval;
    //private int merEW;

    public GameImplementation(int width, int height, Random rand) {
        if (width < 2 || height < 2) {
            throw new IllegalArgumentException("Invalid dimensions");
        }
        if (rand == null) {
            throw new IllegalArgumentException("Random cant be null");
        }
        
        this.random = rand;
        this.gameBoard = new int[height][width];
        this.w = width;
        this.h = height;
        this.moves = 0;
        this.pieces = 0;
        this.points = 0;
       // this.mergeval = 0;
        //this.merEW=0;
        addPiece();
        addPiece();
        
    }
    
    @Override
    public void addPiece() {
        if (!isSpaceLeft()) {
            throw new IllegalStateException("cannot add a new piece");
        }

        int y, x;
        do {
            y = random.nextInt(h);
            x = random.nextInt(w);
        } while (gameBoard[y][x] != 0);

        int value = random.nextDouble() < 0.9 ? 2 : 4;
        gameBoard[y][x] = value;
        pieces++;
    }

    @Override
    public int getBoardHeight() {
        return h;
    }

    @Override
    public int getBoardWidth() {
        return w;
    }

    @Override
    public int getNumMoves() {
        return moves;
    }

    @Override
    public int getNumPieces() {
        return pieces;
    }

    @Override
    public int getPieceAt(int x, int y) {
        if (x < 0 || x >= w || y < 0 || y >= h) {
            throw new IllegalArgumentException("Invalid coordinates");
        }
        return gameBoard[y][x];
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public boolean isMovePossible() {
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (gameBoard[y][x] == 0) {
                    return true;
                } else if (tileMerge(y, x)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isMovePossible(MoveDirection direction) {
        if (direction == null) {
            throw new IllegalArgumentException("Direction cannot be null");
        }
        return isMovePossible(direction, false);
    }

    private boolean isMovePossible(MoveDirection direction, boolean mergeCheck) {
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int val = gameBoard[y][x];
                if (val != 0) {
                    int r = y, c = x;
                    switch (direction) {
                        case NORTH:
                            r = nextRow(y, -1, mergeCheck);
                            break;
                        case SOUTH:
                            r = nextRow(y, 1, mergeCheck);
                            break;
                        case WEST:
                            c = nextColumn(x, -1, mergeCheck);
                            break;
                        case EAST:
                            c = nextColumn(x, 1, mergeCheck);
                            break;
                    }
                    if (r != y || c != x) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int nextRow(int y, int count, boolean mergeCheck) {
        int r = y;
        while (r >= 0 && r < h) {
            if (gameBoard[r][0] == 0 || (mergeCheck && gameBoard[r][0] == gameBoard[y][0])) {
                r += count;
            } else {
                break;
            }
        }
        return r - count;
    }

    private int nextColumn(int x, int count, boolean mergeCheck) {
        int c = x;
        while (c >= 0 && c < w) {
            if (gameBoard[0][c] == 0 || (mergeCheck && gameBoard[0][c] == gameBoard[0][x])) {
                c += count;
            } else {
                break;
            }
        }
        return c - count;
    }

    private boolean tileMerge(int y, int x) {
        int val = gameBoard[y][x];
        if (val == 0) {
            return false;
        }
        if (y > 0 && gameBoard[y - 1][x] == val) {
            return true;
        }
        if (y < h - 1 && gameBoard[y + 1][x] == val) {
            return true;
        }
        if (x > 0 && gameBoard[y][x - 1] == val) {
            return true;
        }
        if (x < w - 1 && gameBoard[y][x + 1] == val) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isSpaceLeft() {
        if (pieces == 0) {
            return true;
        }
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (gameBoard[y][x] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean performMove(MoveDirection direction) {
        if (direction == null) {
            throw new IllegalArgumentException("Direction cannot be null");
        }

        boolean performM = false;
       // mergeval = 0;
        switch (direction) {
            case NORTH:
                performM = northD();
                
                break;
            case SOUTH:
                performM = southD();
                
                break;
            case WEST:
                performM = westD();
               
                break;
            case EAST:
                performM = eastD();
                
                break;
        }

        if (performM) {
            moves++;
        }
        return performM;
    }

    private boolean northD() {
        boolean performM = false;
        for (int x = 0; x < w; x++) {
            int yTemp = 0;
            int preMergeval = 0;
            for (int y = 0; y < h; y++) {
                if (gameBoard[y][x] != 0) {
                    if (preMergeval == gameBoard[y][x]) {
                        gameBoard[yTemp - 1][x] *= 2;
                        points += gameBoard[yTemp - 1][x];
                        preMergeval = 0;
                        gameBoard[y][x] = 0;
                        performM = true;
                    } else {
                        preMergeval = gameBoard[y][x];
                        if (yTemp != y) {
                            gameBoard[yTemp][x] = gameBoard[y][x];
                            gameBoard[y][x] = 0;
                            performM = true;
                        }
                        yTemp++;
                    }
                }
            }
        }
        return performM;
    }

    private boolean southD() {
        boolean performM = false;
        for (int x = 0; x < w; x++) {
            int yTemp = h - 1;
            int preMergeval = 0;
            for (int y = h - 1; y >= 0; y--) {
                if (gameBoard[y][x] != 0) {
                    if (preMergeval == gameBoard[y][x]) {
                        gameBoard[yTemp + 1][x] *= 2;
                        points += gameBoard[yTemp + 1][x];
                        preMergeval = 0;
                        gameBoard[y][x] = 0;
                        performM = true;
                    } else {
                        preMergeval = gameBoard[y][x];
                        if (yTemp != y) {
                            gameBoard[yTemp][x] = gameBoard[y][x];
                            gameBoard[y][x] = 0;
                            performM = true;
                        }
                        yTemp--;
                    }
                }
            }
        }
        return performM;
    }

    private boolean westD() {
        boolean performM = false;
      
        for (int y = 0; y < h; y++) {
            int xTemp = 0;
            int preMergeval = 0;
            for (int x = 0; x < w; x++) {
                int  tempPoints =0;
                if (gameBoard[y][x] != 0) {
                    if (preMergeval == gameBoard[y][x]) {
                        gameBoard[y][xTemp - 1] *= 2;
                        tempPoints += gameBoard[y][xTemp - 1];
                        preMergeval = 0;
                        gameBoard[y][x] = 0;
                        performM = true;
                    } else {
                        preMergeval = gameBoard[y][x];
                        if (xTemp != x) {
                            gameBoard[y][xTemp] = gameBoard[y][x];
                            gameBoard[y][x] = 0;
                            performM = true;
                        }
                        xTemp++;
                    }
                }
                points+=tempPoints;
            }
        }
        return performM;
       
    }

    private boolean eastD() {
        boolean performM = false;
        
        for (int y = 0; y < h; y++) {
            int xTemp = w - 1;
            int preMergeval = 0;
            for (int x = w - 1; x >= 0; x--) {
                int tempPoints=0;
                if (gameBoard[y][x] != 0) {
                    if (preMergeval == gameBoard[y][x]) {
                        gameBoard[y][xTemp + 1] *= 2;
                        tempPoints += gameBoard[y][xTemp + 1];
                        preMergeval = 0;
                        gameBoard[y][x] = 0;
                        performM = true;
                    } else {
                        preMergeval = gameBoard[y][x];
                        if (xTemp != x) {
                            gameBoard[y][xTemp] = gameBoard[y][x];
                            gameBoard[y][x] = 0;
                            performM = true;
                        }
                        xTemp--;
                    }
                }
                points+= tempPoints;
            }
        }
       
        return performM;
    }

    @Override
    public void run(PlayerInterface player, UserInterface ui) {
        if (player == null || ui == null) {
            throw new IllegalArgumentException("Player and UI cannot be null");
        }

        ui.updateScreen(this);

        while (isMovePossible()) {
            MoveDirection movdir = player.getPlayerMove(this, ui);
            if (movdir == null) {
                throw new IllegalArgumentException("No move provided");
            }
            boolean played = performMove(movdir);
            if (played) {
                addPiece();
                ui.updateScreen(this);
            }
        }

        ui.showGameOverScreen(this);
    }

    @Override
    public void setPieceAt(int x, int y, int piece) {
        if (x < 0 || x >= w || y < 0 || y >= h) {
            throw new IllegalArgumentException("Invalid coordinates");
        }
        if (piece < 0 || piece % 2 !=0) {
            throw new IllegalArgumentException("Piece value negative");
        }
        gameBoard[y][x] = piece; 
    
    }

}
