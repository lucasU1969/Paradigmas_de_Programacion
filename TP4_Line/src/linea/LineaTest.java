package linea;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.function.Executable;

public class LineaTest {

    @Test public void test01LineaInstantiatesWithTheCorrectAmountOfColumns() {
        Linea line = new Linea(3, 4, 'C');
        assertTrue(line.isColumnAvailable(1));
        assertTrue(line.isColumnAvailable(2));
        assertTrue(line.isColumnAvailable(3));
    }

    @Test public void test02LineaInstantiatesOnlyWithTheCorrectAmountOfColumns() {
        Linea line = new Linea(3, 4, 'C');
        assertFalse(line.isColumnAvailable(0));
        assertFalse(line.isColumnAvailable(4));
    }

    @Test public void test03RedStarts() {
        Linea line = gameWithARedPiece();
        assertTrue(line.positionContains(1,1, 'R'));
    }

    @Test public void test04GameFailsWhenBlueStarts() {
        Linea line = new Linea(3, 4, 'C');
        assertThrowsLike(() -> line.playBlueAt(1), TurnHandler.PlayerCanOnlyParticipateInTheirTurn);
    }

    @Test public void test05RedTriesToPlayTwice() {
        Linea line = gameWithARedPiece();
        assertThrowsLike(() -> line.playRedkAt(1), TurnHandler.PlayerCanOnlyParticipateInTheirTurn);
    }

    @Test public void test06BlueTriesToPlayTwice() {
        Linea line = gameWithARedPiece();
        line.playBlueAt(1);
        assertThrowsLike(() -> line.playBlueAt(1), TurnHandler.PlayerCanOnlyParticipateInTheirTurn);
    }

    @Test public void test07AddingABluePieceToAnEmptyColumn() {
        Linea line = new Linea(3, 4, 'C');
        line.playRedkAt(3);
        line.playBlueAt(1);
        assertTrue(line.positionContains(1,1, 'B'));
    }

    @Test public void test08TryingToAddABluePieceToAnInvalidColumn() {
        Linea line = new Linea(3, 4, 'C');
        line.playRedkAt(2);
        assertThrowsLike(() -> line.playBlueAt(0), Linea.itSNotPossibleToPlayAtThisPosition);
    }

    @Test public void test09TryingToAddARedPieceToAnInvalidColumn() {
        Linea line = new Linea(3, 4, 'C');
        assertThrowsLike(() -> line.playRedkAt(0),  Linea.itSNotPossibleToPlayAtThisPosition);
    }

    @Test public void test10AddingAPieceUponAnother() {
        Linea line = gameWithARedPiece();
        line.playBlueAt(1);
        assertTrue(line.positionContains(1,1, 'R'));
        assertTrue(line.positionContains(1,2, 'B'));
    }

    @Test public void test11TryingToAddABluePieceToAFullColumn() {
        Linea line = new Linea(3, 4, 'C');
        line.playRedkAt(2);
        line.playBlueAt(1);
        line.playRedkAt(1);
        line.playBlueAt(1);
        line.playRedkAt(1);
        assertThrowsLike(() -> line.playBlueAt(1),  Linea.itSNotPossibleToPlayAtThisPosition);
    }

    @Test public void test12TryingToAddARedPieceToAFullColumn() {
        Linea line = gameWithARedPiece();
        line.playBlueAt(1);
        line.playRedkAt(1);
        line.playBlueAt(1);
        assertThrowsLike(() -> line.playRedkAt(1),  Linea.itSNotPossibleToPlayAtThisPosition);
    }

    @Test public void test13GameFinishesInCaseOfDraw() {
        assertTrue ( draw( gameWithARedPiece()).finished());
    }

    @Test public void test14RedWinsVerticallyWithGameModeA() {
        assertGameFinishedAfterPlayerWon( redWinsVertically( new Linea(3, 4, 'A') ), 'R' );
    }
    @Test public void test15BlueWinsVerticallyWithGameModeA() {
        assertGameFinishedAfterPlayerWon( blueWinsVertically( new Linea(3, 4, 'A') ), 'B' );
    }

    @Test public void test16RedWinsHorizontallyWithGameModeA() {
        assertGameFinishedAfterPlayerWon( redWinsHorizontally( new Linea(5, 4, 'A') ), 'R');
    }

    @Test public void test17BlueWinsHorizontallyWithGameModeA() {
        assertGameFinishedAfterPlayerWon(blueWinsHorizontally(new Linea(5, 4, 'A')), 'B');
    }

    @Test public void test18RedDoesNotWinDiagonallyWithGameModeA() {
        assertFalse( redWinsDiagonally( new Linea(5, 5, 'A') ).won('R') );
    }

    @Test public void test19BlueDoesNotWinDiagonallyWithGameModeA() {
        assertFalse(blueWinsInTheDiagonal(new Linea(5, 5, 'A')).won( 'B' ) );
    }

    @Test public void test20RedDoesNotWinInTheOppositeDiagonalWithGameModeA() {
        assertFalse ( redWinsInTheOppositeDiagonal( new Linea(5, 5, 'A') ).won('R') );
    }

    @Test public void test21BlueDoesNotWinInTheOppositeDiagonalWithGameModeA() {
        assertFalse( blueWinsInTheOppositeDiagonal( new Linea(5, 5, 'A') ).won('B') );
    }

    @Test public void test22RedWinsDiagonallyWithGameModeB() {
        assertGameFinishedAfterPlayerWon( redWinsDiagonally( new Linea(5, 5, 'B') ), 'R');
    }

    @Test public void test23BlueWinsDiagonallyWithGameModeB() {
        assertGameFinishedAfterPlayerWon(blueWinsInTheDiagonal(new Linea(5, 5, 'B')), 'B');
    }

    @Test public void test24RedWinsInTheOppositeDiagonalWithGameModeB() {
        assertGameFinishedAfterPlayerWon( redWinsInTheOppositeDiagonal( new Linea(5, 5, 'B') ), 'R');
    }

    @Test public void test25BlueWinsInTheOppositeDiagonalWithGameModeB() {
        assertGameFinishedAfterPlayerWon(blueWinsInTheOppositeDiagonal(new Linea(5, 5, 'B')), 'B');
    }

    @Test public void test26RedDoesNotWinVerticallyWithGameModeB() {
        assertFalse( redWinsVertically( new Linea(3, 4, 'B') ).won('R') );
    }

    @Test public void test27BlueDoesNotWinVerticallyWithGameModeB() {
        assertFalse( blueWinsVertically( new Linea(3, 4, 'B') ).won('B') );
    }

    @Test public void test28RedDoesNotWinHorizontallyWithGameModeB() {
        assertFalse( redWinsHorizontally( new Linea(5, 4, 'B') ).won('R') );
    }

    @Test public void test29BlueDoesNotWinHorizontallyWithGameModeB() {
        assertFalse( blueWinsHorizontally( new Linea(5, 4, 'B') ).won('B') );
    }

    @Test public void test30RedWinsVerticallyWithGameModeC() {
        assertGameFinishedAfterPlayerWon( redWinsVertically( new Linea(3, 4, 'C') ), 'R');
    }

    @Test public void test31BlueWinsVerticallyWithGameModeC() {
        assertGameFinishedAfterPlayerWon(blueWinsVertically(new Linea(3, 4, 'C')), 'B');
    }

    @Test public void test32RedWinsHorizontallyWithGameModeC() {
        assertGameFinishedAfterPlayerWon( redWinsHorizontally( new Linea(5, 4, 'C') ), 'R');
    }

    @Test public void test33BlueWinsHorizontallyWithGameModeC() {
        assertGameFinishedAfterPlayerWon(blueWinsHorizontally(new Linea(5, 4, 'C')), 'B');
    }

    @Test public void test34RedWinsDiagonallyWithGameModeC() {
        assertGameFinishedAfterPlayerWon( redWinsDiagonally( new Linea(5, 5, 'C') ), 'R');
    }

    @Test public void test35BlueWinsDiagonallyWithGameModeC() {
        assertGameFinishedAfterPlayerWon(blueWinsInTheDiagonal(new Linea(5, 5, 'C')), 'B');
    }

    @Test public void test36RedWinsInTheOppositeDiagonalWithGameModeC() {
        assertGameFinishedAfterPlayerWon(redWinsInTheOppositeDiagonal(new Linea(5, 5, 'C')), 'R');
    }

    @Test public void test37BlueWinsInTheOppositeDiagonalWithGameModeC() {
        assertGameFinishedAfterPlayerWon(blueWinsInTheOppositeDiagonal(new Linea(5, 5, 'C')), 'B');
    }

    @Test public void test38GetStringWorksForAnEmptySlot() {
        Linea line = new Linea(1, 1, 'C');
        assertEquals( "-", line.getPositionAsString(1, 1) );
    }

    @Test public void test39GetRedPositionString() {
        Linea line = new Linea(1, 1, 'C');
        line.playRedkAt(1);
        assertEquals( "R", line.getPositionAsString(1, 1) );
    }

    @Test public void test40GetBluePositionString() {
        Linea line = new Linea(2, 2, 'C');
        line.playRedkAt(2);
        line.playBlueAt(1);
        assertEquals( "B", line.getPositionAsString(1, 1) );
    }

    @Test public void test41ShowIsCorrectForASmallBoard() {
        Linea line = new Linea(1, 1, 'C');
        assertEquals( "| - |\n", line.show() );
    }

    @Test public void test42ShowIsCorrectAfterAddingARedPiece() {
        Linea line = new Linea(1, 1, 'C');
        line.playRedkAt(1);
        assertEquals( "| R |\nEmpate", line.show() );
    }

    @Test public void test43ShowIsCorrectAfterAddingABluePiece() {
        Linea line = new Linea(2, 1, 'C');
        line.playRedkAt(2);
        line.playBlueAt(1);
        assertEquals( "| B | R |\nEmpate", line.show() );
    }

    @Test public void test44AddingARedPieceOnABiggerBoard() {
        Linea line = new Linea( 5, 5, 'C');
        line.playRedkAt(1);
        assertEquals( "| - | - | - | - | - |\n" +
                      "| - | - | - | - | - |\n" +
                      "| - | - | - | - | - |\n" +
                      "| - | - | - | - | - |\n" +
                      "| R | - | - | - | - |\n",
                line.show());
    }

    @Test public void test45TyingToPlayAfterTheGameFinished() {
        Linea line = gameWithARedPiece();
        line.playBlueAt(2);
        line.playRedkAt(1);
        line.playBlueAt(2);
        line.playRedkAt(1);
        line.playBlueAt(2);
        line.playRedkAt(1);
        assertTrue( line.won('R') );
        assertThrowsLike(() -> line.playBlueAt(3), TurnHandler.ItIsNotPossibleToPlayAfterTheGameIsFinished);
    }
    @Test public void test46TryingToPlayWithNotAvailableGameModeThrowsAnException(){
        assertThrows(Exception.class, () -> new Linea(3, 4, 'D'));
    }

    private Linea gameWithARedPiece() {
        Linea line = new Linea(3, 4, 'C');
        line.playRedkAt(1);
        return line;
    }

    private Linea draw(Linea line) {
        line.playBlueAt(2);
        line.playRedkAt(3);
        line.playBlueAt(1);
        line.playRedkAt(2);
        line.playBlueAt(3);
        line.playRedkAt(1);
        line.playBlueAt(2);
        line.playRedkAt(3);
        line.playBlueAt(1);
        line.playRedkAt(2);
        line.playBlueAt(3);
        return line;
    }

    private Linea redWinsVertically(Linea line) {
        line.playRedkAt(1);
        line.playBlueAt(2);
        line.playRedkAt(1);
        line.playBlueAt(2);
        line.playRedkAt(1);
        line.playBlueAt(2);
        line.playRedkAt(1);
        return line;
    }

    private Linea blueWinsVertically(Linea line) {
        line.playRedkAt(2);
        line.playBlueAt(1);
        line.playRedkAt(2);
        line.playBlueAt(1);
        line.playRedkAt(2);
        line.playBlueAt(1);
        line.playRedkAt(3);
        line.playBlueAt(1);
        return line;
    }

    private Linea redWinsHorizontally(Linea line) {
        line.playRedkAt(1);
        line.playBlueAt(1);
        line.playRedkAt(2);
        line.playBlueAt(2);
        line.playRedkAt(3);
        line.playBlueAt(3);
        line.playRedkAt(4);
        return line;
    }

    private Linea blueWinsHorizontally(Linea line) {
        line.playRedkAt(5);
        line.playBlueAt(1);
        line.playRedkAt(1);
        line.playBlueAt(2);
        line.playRedkAt(2);
        line.playBlueAt(3);
        line.playRedkAt(3);
        line.playBlueAt(4);
        return line;
    }

    private Linea redWinsDiagonally(Linea line) {
        line.playRedkAt(1);
        line.playBlueAt(2);
        line.playRedkAt(2);
        line.playBlueAt(1);
        line.playRedkAt(3);
        line.playBlueAt(3);
        line.playRedkAt(3);
        line.playBlueAt(4);
        line.playRedkAt(4);
        line.playBlueAt(4);
        line.playRedkAt(4);
        return line;
    }

    private Linea blueWinsInTheDiagonal(Linea line) {
        line.playRedkAt(5);
        line.playBlueAt(1);
        line.playRedkAt(2);
        line.playBlueAt(2);
        line.playRedkAt(1);
        line.playBlueAt(3);
        line.playRedkAt(3);
        line.playBlueAt(3);
        line.playRedkAt(4);
        line.playBlueAt(4);
        line.playRedkAt(4);
        line.playBlueAt(4);
        return line;
    }

    private Linea redWinsInTheOppositeDiagonal(Linea line) {
        line.playRedkAt(5);
        line.playBlueAt(4);
        line.playRedkAt(4);
        line.playBlueAt(5);
        line.playRedkAt(3);
        line.playBlueAt(3);
        line.playRedkAt(3);
        line.playBlueAt(2);
        line.playRedkAt(2);
        line.playBlueAt(2);
        line.playRedkAt(2);
        return line;
    }

    private Linea blueWinsInTheOppositeDiagonal(Linea line) {
        line.playRedkAt(1);
        line.playBlueAt(5);
        line.playRedkAt(4);
        line.playBlueAt(4);
        line.playRedkAt(5);
        line.playBlueAt(3);
        line.playRedkAt(3);
        line.playBlueAt(3);
        line.playRedkAt(2);
        line.playBlueAt(2);
        line.playRedkAt(2);
        line.playBlueAt(2);
        return line;
    }

    private void assertGameFinishedAfterPlayerWon( Linea line, char player ) {
        assertTrue( line.won( player ) );
        assertTrue( line.finished() );
    }
    private void assertThrowsLike(Executable ex, String msg) {
        assertEquals(msg, assertThrows(Exception.class, ex).getMessage());
    }

}