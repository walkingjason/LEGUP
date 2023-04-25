package puzzles.treetent.rules;

import legup.MockGameBoardFacade;
import legup.TestUtilities;
import edu.rpi.legup.model.tree.TreeNode;
import edu.rpi.legup.model.tree.TreeTransition;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.rpi.legup.puzzle.treetent.TreeTent;
import edu.rpi.legup.puzzle.treetent.TreeTentBoard;
import edu.rpi.legup.puzzle.treetent.TreeTentCell;
import edu.rpi.legup.puzzle.treetent.TreeTentType;
import edu.rpi.legup.puzzle.treetent.rules.NoTreeForTentContradictionRule;
import edu.rpi.legup.save.InvalidFileFormatException;

import java.awt.*;

public class NoTreeForTentContradictionRuleTest {

    private static final NoTreeForTentContradictionRule RULE = new NoTreeForTentContradictionRule();
    private static TreeTent treetent;

    @BeforeClass
    public static void setUp() {
        MockGameBoardFacade.getInstance();
        treetent = new TreeTent();
    }

    @Test
    public void NoTreeForTentContradictionRule_() throws InvalidFileFormatException {
        TestUtilities.importTestBoard("puzzles/treetent/rules/NoTreeForTentContradictionRule/NoTreeForTent", treetent);
        TreeNode rootNode = treetent.getTree().getRootNode();
        TreeTransition transition = rootNode.getChildren().get(0);
        transition.setRule(RULE);

        TreeTentBoard board = (TreeTentBoard) transition.getBoard();

        Assert.assertNull(RULE.checkContradiction(board));
        Assert.assertNull(RULE.checkRuleAt(transition, board.getCell(0, 0)));
        Assert.assertNotNull(RULE.checkRuleAt(transition, board.getCell(0, 1)));
        Assert.assertNotNull(RULE.checkRuleAt(transition, board.getCell(1, 0)));
        Assert.assertNotNull(RULE.checkRuleAt(transition, board.getCell(1, 1)));
    }
}
