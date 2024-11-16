package ttfe.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import ttfe.MoveDirection;
import ttfe.SimulatorInterface;
import ttfe.TTFEFactory;
//import ttfe.PlayerInterface;
//import ttfe.UserInterface;


public class SimpleTests {

	private SimulatorInterface game;
	//private SimulatorInterface myGame;

	/*@Before
	public void mySetUp() {
		myGame = TTFEFactory.createSimulator(4, 4, new Random(0));
	}*/

	@Before
	public void setUp() {
		game = TTFEFactory.createSimulator(4, 4, new Random(0)); 
	}
	
	@Test
	public void testInitialGamePoints() {
		assertEquals("The initial game did not have zero points", 0,
				game.getPoints());
	}
	
	@Test
	public void testInitialBoardHeight() {
		assertTrue("The initial game board did not have correct height",
				4 == game.getBoardHeight());
	}

	@Test
	public void testConstructorMakeBoard() {
		assertEquals("Make board widht not correct", 4, game.getBoardWidth());
		assertEquals("Board height not correct", 4, game.getBoardHeight());
	}
    
	@Test
	public void testInitialScores() {
		assertEquals("Initial score not correct", 0, game.getPoints());
	}
    
	@Test
    public void testInitialMove() {
		assertEquals("Wrong number of moves", 0, game.getNumMoves());
	}

	@Test
	public void testConstructorInvalidWH() {
		assertThrows("Constructor not thow illegal arg exception for invalid dimension", IllegalArgumentException.class, () -> TTFEFactory.createSimulator(1,4,new Random()));
		assertThrows("Constructor not thow illegal arg exception for invalid dimension", IllegalArgumentException.class, () -> TTFEFactory.createSimulator(4,1,new Random()));
	}

	@Test
	public void testConstructorPlacingTiles() {
		int count = 0;
		for(int i=0; i<game.getBoardHeight();i++) {
			for(int j=0; j<game.getBoardWidth();j++) {
				if(game.getPieceAt(i,j)!=0) {
					count++;

				}
			}
		}
		assertEquals("Game not have 2 tiles", 2, count);
	}

	@Test
	public void testConstructorTilesValue() {
		int tile2 = 0, tile4=0;
		for(int i=0;i< game.getBoardHeight();i++) {
			for(int j=0;j < game.getBoardWidth();j++) {
				int value = game.getPieceAt(i,j);
				if(value ==2) {
					tile2++;
				} else if(value ==4) {
					tile4++;
				}
			}
		}
		int count = tile2 + tile4;
		assertEquals("Game not have 2 tiles", 2,count );
	}


	@Test
	public void testAddPieceOnFullBoard() {
		for (int i= 0;i< game.getBoardHeight(); i++) {
			for (int j= 0; j< game.getBoardWidth(); j++) {
				game.setPieceAt(i, j, 2);
			}
		}
        assertThrows("Adding a piece on a full board did not throw IllegalStateException", IllegalStateException.class, () -> game.addPiece());
    }

	@Test 
	public void testAddPieceNonFull() {
		int pieceCount = game.getNumPieces();
		game.addPiece();
		assertEquals("Piece not added", pieceCount + 1, game.getNumPieces());
	}

	@Test
	public void testGetNumMove() {
		game.performMove(MoveDirection.EAST);
		game.performMove(MoveDirection.WEST);
		assertEquals("Inccorect number of moves", 2, game.getNumMoves());
	}

	@Test
	public void testGetNumMove2() {
		game.performMove(MoveDirection.NORTH);
		game.performMove(MoveDirection.WEST);
		game.performMove(MoveDirection.SOUTH);
		assertEquals("Inccorect number of moves", 3, game.getNumMoves());
	}
	
	@Test
	public void testGetNumPiecesSofort() {
		assertEquals("Incorrect number of pieces", 2, game.getNumPieces());
	}
    

	@Test
    public void testGetNumPiecesAdd() {
		int count = game.getNumPieces();
		game.addPiece();
		assertEquals("Number of pieces not correct", count +1, game.getNumPieces());
	}

	@Test
    public void testGetNumPiecesAdd2() {
		int count = game.getNumPieces();
		game.addPiece();
		game.addPiece();
		game.addPiece();
		assertEquals("Number of pieces not correct", count +3, game.getNumPieces());
	}
    
    @Test
	public void testGetPoints() {
		game.setPieceAt(0, 0, 2);
		game.setPieceAt(0,1 , 2);
		game.performMove(MoveDirection.NORTH);
		assertEquals("Incorrect score addition", 4, game.getPoints());
	}


	@Test
	public void testGetPoints3() {
		game.setPieceAt(1, 2, 2);
		game.setPieceAt(1,3 , 2);
		game.performMove(MoveDirection.SOUTH);
		assertEquals("Incorrect score addition", 4, game.getPoints());
	}

	@Test
	public void testGetPoints4() {
		game.setPieceAt(0, 2, 2);
		game.setPieceAt(0,3 , 2);
		game.performMove(MoveDirection.EAST);
		assertEquals("Incorrect score addition", 4, game.getPoints());
	}
    // recheck
	@Test
	public void testGetPieAt() {
		game.setPieceAt(0, 0, 2);
		game.setPieceAt(0, 1, 2);
		game.performMove(MoveDirection.NORTH);
		assertEquals("Tile value not correct", 4, game.getPieceAt(0, 0));
		assertEquals("Tile value not correct", 0, game.getPieceAt(0, 1));

	}

	@Test
	public void testGetPieceAt() {
		game.setPieceAt(1, 2, 2);
		assertEquals("Tile value not correct", 2, game.getPieceAt(1, 2));
		game.setPieceAt(2, 3, 4);
		assertEquals("Tile value not correct", 4, game.getPieceAt(2, 3));
	}

	@Test
	public void testGetPieceAt2() {
		game.setPieceAt(2, 2, 4);
		assertEquals("Tile value not correct", 4, game.getPieceAt(2, 2));
		game.setPieceAt(3, 3, 4);
		assertEquals("Tile value not correct", 4, game.getPieceAt(3, 3));
	}

	@Test
    public void testGetPieceAtWrongC() {
        assertThrows("getPieceAt invalid coordinates not throw IllegalArgumentException", IllegalArgumentException.class, () -> game.getPieceAt(-1, 0));
        assertThrows("getPieceAt invalid coordinates not throw IllegalArgumentException", IllegalArgumentException.class, () -> game.getPieceAt(0, -1));
        assertThrows("getPieceAt invalid coordinates not throw IllegalArgumentException",IllegalArgumentException.class, () -> game.getPieceAt(4, 0));
        assertThrows("getPieceAt invalid coordinates not throw IllegalArgumentException", IllegalArgumentException.class, () -> game.getPieceAt(0, 4));
    }

	@Test
	public void testSetPieceAtRightC() {
		game.setPieceAt(1, 2, 4);
        assertEquals("Tile value not correct", 4, game.getPieceAt(1, 2));
	}
    
	@Test
    public void testSetPieceAtNeg() {
        assertThrows("setPiece value negative did not throw IllegalArgumentException",IllegalArgumentException.class, () -> game.setPieceAt(1, 1, -2));
    }

	@Test
	public void testSetPieceAtWrongC() {
        assertThrows("setPieceAt invalid coordinates not throw IllegalArgumentException",IllegalArgumentException.class, () -> game.setPieceAt(-1, 0, 2));
        assertThrows("setPieceAt invalid coordinates not throw IllegalArgumentException",IllegalArgumentException.class, () -> game.setPieceAt(0, -1, 2));
        assertThrows("setPieceAt invalid coordinates not throw IllegalArgumentException",IllegalArgumentException.class, () -> game.setPieceAt(4, 0, 2));
        assertThrows("setPieceAt invalid coordinates not throw IllegalArgumentException",IllegalArgumentException.class, () -> game.setPieceAt(0, 4, 2));
    }

	@Test
	public void moveDirectionNull() {
		assertThrows("Did not throw IllegalArgumentException for null move direction", IllegalArgumentException.class,() -> game.isMovePossible(null));
	}
    
    /*@Test
	public void testMovep1() {
		for(int i=0;i<game.getBoardHeight();i++) {
			for(int j=0;j<game.getBoardWidth();j++) {
                 game.setPieceAt(i, j, 0);
			}
		}
		game.setPieceAt(0, 0, 2);
		
		assertTrue(game.isMovePossible());
		assertTrue(game.isMovePossible(MoveDirection.SOUTH));
		assertTrue(game.isMovePossible(MoveDirection.EAST));
		assertFalse(game.isMovePossible(MoveDirection.NORTH));
		assertFalse(game.isMovePossible(MoveDirection.WEST));
	}*/
	@Test
	public void emptyBoardisMovePossible3() { //south east
		game.setPieceAt(0, 0, 2);
		game.setPieceAt(0, 1, 4);
		game.setPieceAt(0, 2, 8);
		game.setPieceAt(0, 3, 16);
		game.setPieceAt(1, 0, 4);
		game.setPieceAt(1, 1, 8);
		game.setPieceAt(1, 2, 2);
		game.setPieceAt(1, 3, 4);
		game.setPieceAt(2, 0, 2);
		game.setPieceAt(2, 1, 4);
		game.setPieceAt(2, 2, 2);
		game.setPieceAt(2, 3, 16);
		game.setPieceAt(3, 0, 4);
		game.setPieceAt(3, 1, 8);
		game.setPieceAt(3, 2, 4);
		game.setPieceAt(3, 3, 2);
        assertTrue(game.isMovePossible());
        assertTrue(game.isMovePossible(MoveDirection.EAST));
		game.performMove(MoveDirection.EAST);
        assertEquals(4, game.getPieceAt(2, 2));
		assertTrue(game.isMovePossible());
		assertTrue(game.isMovePossible(MoveDirection.EAST));
		game.performMove(MoveDirection.EAST);
		assertEquals(8, game.getPieceAt(3, 2));
	
	}
    
	/*@Test
	public void mergetile() { //south east
		
		game.setPieceAt(0, 0, 4);
		game.setPieceAt(1, 0, 4);
		
		
		game.performMove(MoveDirection.WEST);
        assertEquals(8, game.getPieceAt(0, 0));
		
		int points = game.getPoints();
		assertEquals(8, points);

		
	
	}*/

//here
	@Test
	public void emptyBoardisMovePossible7() { //south east
		game.setPieceAt(0, 0, 2);
		game.setPieceAt(0, 1, 2);
		game.setPieceAt(0, 2, 8);
		game.setPieceAt(0, 3, 8);
		game.setPieceAt(1, 0, 4);
		game.setPieceAt(1, 1, 8);
		game.setPieceAt(1, 2, 2);
		game.setPieceAt(1, 3, 4);
		game.setPieceAt(2, 0, 2);
		game.setPieceAt(2, 1, 4);
		game.setPieceAt(2, 2, 8);
		game.setPieceAt(2, 3, 16);
		game.setPieceAt(3, 0, 4);
		game.setPieceAt(3, 1, 8);
		game.setPieceAt(3, 2, 16);
		game.setPieceAt(3, 3, 2);
		assertTrue(game.isMovePossible());
        assertTrue(game.isMovePossible(MoveDirection.NORTH));
		game.performMove(MoveDirection.NORTH);
        assertEquals(4, game.getPieceAt(0, 0));
		assertEquals(16, game.getPieceAt(0, 1));
	
	}


	

	@Test
	public void emptyBoardisMovePossible3a() { //north east
		game.setPieceAt(0, 0, 2);
		game.setPieceAt(0, 1, 4);
		game.setPieceAt(0, 2, 8);
		game.setPieceAt(0, 3, 16);
		game.setPieceAt(1, 0, 4);
		game.setPieceAt(1, 1, 8);
		game.setPieceAt(1, 2, 2);
		game.setPieceAt(1, 3, 4);
		game.setPieceAt(2, 0, 2);
		game.setPieceAt(2, 1, 4);
		game.setPieceAt(2, 2, 8);
		game.setPieceAt(2, 3, 16);
		game.setPieceAt(3, 0, 4);
		game.setPieceAt(3, 1, 8);
		game.setPieceAt(3, 2, 2);
		game.setPieceAt(3, 3, 2);
		assertTrue(game.isMovePossible());
        assertTrue(game.isMovePossible(MoveDirection.SOUTH));
		game.performMove(MoveDirection.SOUTH);
		assertEquals(4, game.getPieceAt(3, 3));
		
	}

	@Test
	public void emptyBoardisMovePossible4() { //north west
		game.setPieceAt(0, 0, 16);
		game.setPieceAt(0, 1, 4);
		game.setPieceAt(0, 2, 8);
		game.setPieceAt(0, 3, 16);
		game.setPieceAt(1, 0, 4);
		game.setPieceAt(1, 1, 4);
		game.setPieceAt(1, 2, 2);
		game.setPieceAt(1, 3, 4);
		game.setPieceAt(2, 0, 2);
		game.setPieceAt(2, 1, 4);
		game.setPieceAt(2, 2, 8);
		game.setPieceAt(2, 3, 16);
		game.setPieceAt(3, 0, 4);
		game.setPieceAt(3, 1, 4);
		game.setPieceAt(3, 2, 16);
		game.setPieceAt(3, 3, 2);
		assertTrue(game.isMovePossible());
        assertTrue(game.isMovePossible(MoveDirection.WEST));
		game.performMove(MoveDirection.WEST);
        assertEquals(8, game.getPieceAt(0, 1));
		assertEquals(8, game.getPieceAt(1, 1));
		assertTrue(game.isMovePossible());
		assertTrue(game.isMovePossible(MoveDirection.WEST));
		game.performMove(MoveDirection.WEST);
		assertEquals(16, game.getPieceAt(0, 1));
		assertTrue(game.isMovePossible());
		assertTrue(game.isMovePossible(MoveDirection.NORTH));
		game.performMove(MoveDirection.NORTH);
		assertEquals(32, game.getPieceAt(0, 0));

	}

	/*@Test
	public void emptyBoardisMovePossible4a() { //south west
		game.setPieceAt(3, 0, 2);
		game.setPieceAt(3, 1, 4);
		game.setPieceAt(3, 2, 8);
		assertTrue("isMovePossible returned false when move is possible", game.isMovePossible(MoveDirection.SOUTH));
		assertTrue("isMovePossible returned false when move is possible", game.isMovePossible(MoveDirection.WEST));
		
	}*/


	/*@Test
	public void fullBoardisMovePossible() {
	    int val =2;
		for(int i=0;i<game.getBoardHeight();i++) {
			for(int j=0;j<game.getBoardWidth();j++) {
				game.setPieceAt(i, j, val);
				val = val*2;
			}
		}
		assertFalse("isMovePossible returned true when no move possible", game.isMovePossible(MoveDirection.SOUTH));
		assertFalse("isMovePossible returned true when no move possible", game.isMovePossible(MoveDirection.NORTH));
		assertFalse("isMovePossible returned true when no move possible", game.isMovePossible(MoveDirection.EAST));
		assertFalse("isMovePossible returned true when no move possible", game.isMovePossible(MoveDirection.WEST));
	}*/

	
   /*  @Test
    public void testMovePossible1() {
    game.setPieceAt(0, 0, 2);
    game.setPieceAt(0, 1, 4);
    game.setPieceAt(0, 2, 8);
    game.setPieceAt(0, 3, 16);
    assertFalse("isMovePossible returned true when no move is possible", game.isMovePossible(MoveDirection.NORTH));
	assertFalse("isMovePossible returned true when no move is possible", game.isMovePossible(MoveDirection.SOUTH));
	assertFalse("isMovePossible returned true when no move is possible", game.isMovePossible(MoveDirection.WEST));
	assertTrue("isMovePossible returned true when no move is possible", game.isMovePossible(MoveDirection.EAST));
	}*/
	

	@Test
	public void testIsSpaceLeftInitial() {
		assertTrue("The initial game board not have space left", game.isSpaceLeft());
	}

	@Test
	public void testIsSpaceLeftFinal() {
		int val =2;
		for (int i= 0;i< game.getBoardHeight(); i++) {
			for (int j= 0; j< game.getBoardWidth(); j++) {
				game.setPieceAt(i, j, val);
				val = val*2;
			}
		}
		assertFalse("The final game board have space left", game.isSpaceLeft());
	}

	@Test
	public void performMoveNull() {
        assertThrows("Did not throw IllegalArgumentException for null direction",IllegalArgumentException.class, () -> game.performMove(null));
	}

	@Test
	public void performMoveBoard() {
        game.setPieceAt(0, 0, 2);
        game.setPieceAt(0, 1, 2);
        game.setPieceAt(0, 2, 2);
        game.setPieceAt(0, 3, 2);
		
        assertTrue("Could not perform move", game.performMove(MoveDirection.NORTH));

    }

	@Test
	public void performMoveBoard1() {
        game.setPieceAt(3, 0, 2);
        game.setPieceAt(3, 1, 2);
        game.setPieceAt(3, 2, 2);
        game.setPieceAt(3, 3, 2);
		
        assertTrue("Could not perform move", game.performMove(MoveDirection.SOUTH));

    }

	@Test
	public void performMoveBoard2() {
        game.setPieceAt(0, 0, 2);
        game.setPieceAt(1, 0, 2);
        game.setPieceAt(2, 0, 2);
        game.setPieceAt(3, 0, 2);
		
        assertTrue("Could not perform move", game.performMove(MoveDirection.WEST));

    }

	@Test
	public void performMoveBoard3() {
        game.setPieceAt(0, 3, 2);
        game.setPieceAt(1, 3, 2);
        game.setPieceAt(2, 3, 2);
        game.setPieceAt(3, 3, 2);
		
        assertTrue("Could not perform move", game.performMove(MoveDirection.EAST));

    }


	@Test
    public void performMoveScore() {
        game.setPieceAt(0, 0, 4);
        game.setPieceAt(0, 1, 4);
		game.performMove(MoveDirection.NORTH);
        assertEquals("Could not perform move", 8, game.getPoints());
       
    }


	@Test
	public void testRunPUiNull() {
        assertThrows("Did not throw IllegalArgumentException for null player and user interface", IllegalArgumentException.class, () -> game.run(null, null));
	}

	/*@Test
	public void testRun2() {
		PlayerInterface myPlayer = TTFEFactory.createPlayer(false);
		UserInterface myUi = TTFEFactory.createUserInterface(myGame);
		myGame.run(myPlayer, myUi);
		myUi.showGameOverScreen(myGame);
		assertFalse(myGame.isMovePossible());
	}*/

	@Test
	public void testIsMovA() {
		for(int i=0;i<game.getBoardHeight();i++){
			for(int j=0;j<game.getBoardWidth();j++) {
				game.setPieceAt(i, j, 0);
			}
		}

		game.setPieceAt(0, 0, 4);
		game.setPieceAt(0, 2, 2);
		game.setPieceAt(0, 3, 2);
		assertTrue(game.isMovePossible());
		assertTrue(game.isMovePossible(MoveDirection.SOUTH));
		game.performMove(MoveDirection.SOUTH);
		assertEquals(4, game.getPieceAt(0, 2));
		assertEquals(4, game.getPieceAt(0, 3));
		assertTrue(game.isMovePossible());
		assertTrue(game.isMovePossible(MoveDirection.NORTH));
		game.performMove(MoveDirection.NORTH);
		assertEquals(8, game.getPieceAt(0, 0));
	}

	@Test
	public void testIsMovB() {
		for(int i=0;i<game.getBoardHeight();i++){
			for(int j=0;j<game.getBoardWidth();j++) {
				game.setPieceAt(i, j, 0);
			}
		}

		game.setPieceAt(0, 0, 2);
		game.setPieceAt(0, 1, 2);
		game.setPieceAt(0, 2, 2);
		game.setPieceAt(0, 3, 2);
		assertTrue(game.isMovePossible());
		assertTrue(game.isMovePossible(MoveDirection.NORTH));
		game.performMove(MoveDirection.NORTH);
		assertEquals(4, game.getPieceAt(0, 0));
		assertEquals(4, game.getPieceAt(0, 1));
		assertTrue(game.isMovePossible());
		assertTrue(game.isMovePossible(MoveDirection.SOUTH));
		game.performMove(MoveDirection.SOUTH);
		assertEquals(8, game.getPieceAt(0, 3));
	}

	@Test
	public void testIsMovC() {
		for(int i=0;i<game.getBoardHeight();i++){
			for(int j=0;j<game.getBoardWidth();j++) {
				game.setPieceAt(i, j, 0);
			}
		}

		game.setPieceAt(1, 1, 2);
		game.setPieceAt(2, 2, 2);
		game.setPieceAt(3, 3, 2);
		assertTrue(game.isMovePossible());
		assertTrue(game.isMovePossible(MoveDirection.EAST));
		game.performMove(MoveDirection.EAST);
		assertEquals(2, game.getPieceAt(3, 1));
		assertEquals(2, game.getPieceAt(3, 2));
		assertEquals(2, game.getPieceAt(3, 3));
		assertTrue(game.isMovePossible());
		assertTrue(game.isMovePossible(MoveDirection.SOUTH));
		game.performMove(MoveDirection.SOUTH);
		assertEquals(2, game.getPieceAt(3, 2));
		assertEquals(4, game.getPieceAt(3, 3));
	}

	@Test
	public void testIsMovD() {
		for(int i=0;i<game.getBoardHeight();i++){
			for(int j=0;j<game.getBoardWidth();j++) {
				game.setPieceAt(i, j, 0);
			}
		}

		game.setPieceAt(1, 1, 2);
		game.setPieceAt(2, 2, 2);
		game.setPieceAt(3, 3, 2);
		assertTrue(game.isMovePossible());
		assertTrue(game.isMovePossible(MoveDirection.EAST));
		game.performMove(MoveDirection.EAST);
		assertEquals(2, game.getPieceAt(3, 1));
		assertEquals(2, game.getPieceAt(3, 2));
		assertEquals(2, game.getPieceAt(3, 3));
		assertTrue(game.isMovePossible());
		assertTrue(game.isMovePossible(MoveDirection.NORTH));
		game.performMove(MoveDirection.NORTH);
		assertEquals(4, game.getPieceAt(3, 0));
		assertEquals(2, game.getPieceAt(3, 1));
	}
	


	}