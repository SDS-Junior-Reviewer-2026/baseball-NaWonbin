package baseball;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {
    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();

    }

    private void assertIllegalArgument(String guessNumber) {
        try {
            game.guess(guessNumber);
            fail();
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void throwIllegalArgumentExceptionInvalidUnput() {
        assertIllegalArgument(null);
        assertIllegalArgument("12");
        assertIllegalArgument("1234");
        assertIllegalArgument("12s");
        assertIllegalArgument("121");
    }

    private void generateQuestion(String questionNumer) {
        game.question = questionNumer;
    }

    private void assertMatchedNumber(GuessResult result, boolean solved, int strikes, int balls) {
        assertThat(result).isNotNull();
        assertThat(result.isSolved()).isEqualTo(solved);
        assertThat(result.getStrikes()).isEqualTo(strikes);
        assertThat(result.getBalls()).isEqualTo(balls);
    }

    @Test
    void createGame() {
        assertNotNull(game);
    }

    @Test
    public void 숫자_세개가_전부_일치_할_경우_3_strike() {
        generateQuestion("123");
        assertMatchedNumber(game.guess("123"), true, 3, 0);
    }

    @Test
    public void 숫자_세개가_전부_일치_하지_않을_경우_0_strike_0_ball() {
        generateQuestion("123");
        assertMatchedNumber(game.guess("456"), false, 0, 0);

    }

    @Test
    public void 스트라이크만_있을_경우_1_strike_0_ball() {
        generateQuestion("123");
        assertMatchedNumber(game.guess("120"), false, 2, 0);

    }

    @Test
    public void 볼만_있을_경우_0_strike_1_ball() {
        generateQuestion("123");
        assertMatchedNumber(game.guess("061"), false, 0, 1);

    }

    @Test
    public void 볼과_스트라이크가_함께_있을_경우_1_strike_1_ball() {
        generateQuestion("123");
        assertMatchedNumber(game.guess("136"), false, 1, 1);

    }
}
