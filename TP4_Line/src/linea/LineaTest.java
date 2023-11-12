package linea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.function.Executable;

public class LineaTest {

    public Linea line2x1C;
    public Linea line1x1C;
    public Linea line2x2C;
    public Linea line5x5C;
    public Linea line5x4C;
    public Linea line5x4B;
    public Linea line3x4B;
    public Linea line5x5A;
    public Linea line5x5B;
    public Linea line5x4A;
    public Linea line3x4C;
    public Linea line3x4A;

    @BeforeEach void setUp(){

        line3x4C = new Linea(3, 4, 'C');
        line3x4A = new Linea(3, 4, 'A');
        line5x4A = new Linea(5, 4, 'A');
        line5x5B = new Linea(5, 5, 'B');
        line3x4B = new Linea(3, 4, 'B');
        line5x5A = new Linea(5, 5, 'A');
        line5x4B = new Linea(5, 4, 'B');
        line5x4C = new Linea(5, 4, 'C');
        line5x4B = new Linea(5, 4, 'B');
        line5x5C = new Linea(5, 5, 'C');
        line1x1C = new Linea(1, 1, 'C');
        line2x2C = new Linea(2, 2, 'C');
        line2x1C = new Linea(2, 1, 'C');

    }

    @Test public void test01LineaInstantiatesWithTheCorrectAmountOfColumns() {
        assertTrue(line3x4C.isColumnAvailable(1));
        assertTrue(line3x4C.isColumnAvailable(2));
        assertTrue(line3x4C.isColumnAvailable(3));
    }

    @Test public void test02LineaInstantiatesOnlyWithTheCorrectAmountOfColumns() {
        assertFalse(line3x4C.isColumnAvailable(0));
        assertFalse(line3x4C.isColumnAvailable(4));
    }

    @Test public void test03RedStarts() {
        Linea line = gameWithARedPiece();
        assertTrue(line.positionContains(1,1, 'R'));
    }

    @Test public void test04GameFailsWhenBlueStarts() {
        assertThrowsLike(() -> line3x4C.playBlueAt(1), TurnHandler.PlayerCanOnlyParticipateInTheirTurn);
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
        line3x4C.playRedkAt(3);
        line3x4C.playBlueAt(1);
        assertTrue(line3x4C.positionContains(1,1, 'B'));
    }

    @Test public void test08TryingToAddABluePieceToAnInvalidColumn() {
        line3x4C.playRedkAt(2);
        assertThrowsLike(() -> line3x4C.playBlueAt(0), Linea.itSNotPossibleToPlayAtThisPosition);
    }

    @Test public void test09TryingToAddARedPieceToAnInvalidColumn() {
        assertThrowsLike(() -> line3x4C.playRedkAt(0),  Linea.itSNotPossibleToPlayAtThisPosition);
    }

    @Test public void test10AddingAPieceUponAnother() {
        Linea line = gameWithARedPiece();
        line.playBlueAt(1);
        assertTrue(line.positionContains(1,1, 'R'));
        assertTrue(line.positionContains(1,2, 'B'));
    }

    @Test public void test11TryingToAddABluePieceToAFullColumn() {
        line3x4C.playRedkAt(2);
        line3x4C.playBlueAt(1);
        line3x4C.playRedkAt(1);
        line3x4C.playBlueAt(1);
        line3x4C.playRedkAt(1);
        assertThrowsLike(() -> line3x4C.playBlueAt(1),  Linea.itSNotPossibleToPlayAtThisPosition);
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
        assertGameFinishedAfterPlayerWon( redWinsVertically(line3x4A), 'R' );
    }
    @Test public void test15BlueWinsVerticallyWithGameModeA() {
        assertGameFinishedAfterPlayerWon( blueWinsVertically(line3x4A), 'B' );
    }

    @Test public void test16RedWinsHorizontallyWithGameModeA() {
        assertGameFinishedAfterPlayerWon( redWinsHorizontally(line5x4A), 'R');
    }

    @Test public void test17BlueWinsHorizontallyWithGameModeA() {
        assertGameFinishedAfterPlayerWon(blueWinsHorizontally(line5x4A), 'B');
    }

    @Test public void test18RedDoesNotWinDiagonallyWithGameModeA() {
        assertFalse( redWinsDiagonally(line5x5A).won('R') );
    }

    @Test public void test19BlueDoesNotWinDiagonallyWithGameModeA() {
        assertFalse(blueWinsInTheDiagonal(line5x5A).won( 'B' ) );
    }

    @Test public void test20RedDoesNotWinInTheOppositeDiagonalWithGameModeA() {
        assertFalse ( redWinsInTheOppositeDiagonal(line5x5A).won('R') );
    }

    @Test public void test21BlueDoesNotWinInTheOppositeDiagonalWithGameModeA() {
        assertFalse( blueWinsInTheOppositeDiagonal(line5x5A).won('B') );
    }

    @Test public void test22RedWinsDiagonallyWithGameModeB() {
        assertGameFinishedAfterPlayerWon( redWinsDiagonally(line5x5B), 'R');
    }

    @Test public void test23BlueWinsDiagonallyWithGameModeB() {
        assertGameFinishedAfterPlayerWon(blueWinsInTheDiagonal(line5x5B), 'B');
    }

    @Test public void test24RedWinsInTheOppositeDiagonalWithGameModeB() {
        assertGameFinishedAfterPlayerWon( redWinsInTheOppositeDiagonal(line5x5B), 'R');
    }

    @Test public void test25BlueWinsInTheOppositeDiagonalWithGameModeB() {
        assertGameFinishedAfterPlayerWon(blueWinsInTheOppositeDiagonal(line5x5B), 'B');
    }

    @Test public void test26RedDoesNotWinVerticallyWithGameModeB() {
        assertFalse( redWinsVertically(line3x4B).won('R') );
    }

    @Test public void test27BlueDoesNotWinVerticallyWithGameModeB() {
        assertFalse( blueWinsVertically(line3x4B).won('B') );
    }

    @Test public void test28RedDoesNotWinHorizontallyWithGameModeB() {
        assertFalse( redWinsHorizontally(line5x4B).won('R') );
    }

    @Test public void test29BlueDoesNotWinHorizontallyWithGameModeB() {
        assertFalse( blueWinsHorizontally(line5x4B).won('B') );
    }

    @Test public void test30RedWinsVerticallyWithGameModeC() {
        assertGameFinishedAfterPlayerWon( redWinsVertically(line3x4C), 'R');
    }

    @Test public void test31BlueWinsVerticallyWithGameModeC() {
        assertGameFinishedAfterPlayerWon(blueWinsVertically(line3x4C), 'B');
    }

    @Test public void test32RedWinsHorizontallyWithGameModeC() {
        assertGameFinishedAfterPlayerWon( redWinsHorizontally(line5x4C), 'R');
    }

    @Test public void test33BlueWinsHorizontallyWithGameModeC() {
        assertGameFinishedAfterPlayerWon(blueWinsHorizontally(line5x4C), 'B');
    }

    @Test public void test34RedWinsDiagonallyWithGameModeC() {
        assertGameFinishedAfterPlayerWon( redWinsDiagonally(line5x5C), 'R');
    }

    @Test public void test35BlueWinsDiagonallyWithGameModeC() {
        assertGameFinishedAfterPlayerWon(blueWinsInTheDiagonal(line5x5C), 'B');
    }

    @Test public void test36RedWinsInTheOppositeDiagonalWithGameModeC() {
        assertGameFinishedAfterPlayerWon(redWinsInTheOppositeDiagonal(line5x5C), 'R');
    }

    @Test public void test37BlueWinsInTheOppositeDiagonalWithGameModeC() {
        assertGameFinishedAfterPlayerWon(blueWinsInTheOppositeDiagonal(line5x5C), 'B');
    }

    @Test public void test38GetStringWorksForAnEmptySlot() {
        assertEquals( "-", line1x1C.getPositionAsString(1, 1) );
    }

    @Test public void test39GetRedPositionString() {
        line1x1C.playRedkAt(1);
        assertEquals( "R", line1x1C.getPositionAsString(1, 1) );
    }

    @Test public void test40GetBluePositionString() {
        line2x2C.playRedkAt(2);
        line2x2C.playBlueAt(1);
        assertEquals( "B", line2x2C.getPositionAsString(1, 1) );
    }

    @Test public void test41ShowIsCorrectForASmallBoard() {
        assertEquals( "| - |\n", line1x1C.show() );
    }

    @Test public void test42ShowIsCorrectAfterAddingARedPiece() {
        line1x1C.playRedkAt(1);
        assertEquals( "| R |\nEmpate", line1x1C.show() );
    }

    @Test public void test43ShowIsCorrectAfterAddingABluePiece() {
        line2x1C.playRedkAt(2);
        line2x1C.playBlueAt(1);
        assertEquals( "| B | R |\nEmpate", line2x1C.show() );
    }

    @Test public void test44AddingARedPieceOnABiggerBoard() {
        line5x5C.playRedkAt(1);
        assertEquals( "| - | - | - | - | - |\n" +
                      "| - | - | - | - | - |\n" +
                      "| - | - | - | - | - |\n" +
                      "| - | - | - | - | - |\n" +
                      "| R | - | - | - | - |\n",
                line5x5C.show());
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
        Linea line = line3x4C;
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