package main;

public class GameEngine {
  int[] rolls;
  int currentRoll;

  private static final Integer TOTAL_FRAMES = 10;
  private static final Integer TOTAL_PINS = 10;

  public GameEngine() {
    this.rolls = new int[21];
  }

  public void roll(int pins) {
    rolls[currentRoll++] = pins;
  }

  public int computeScore() {
    int score = 0;
    int frame = 0;

    for (int i = 0; i < TOTAL_FRAMES; i++) {
      if (isStrike(frame)) {
        score += TOTAL_PINS + strikeBonus(frame);
        frame++;
      } else if (isSpare(frame)) {
        score += TOTAL_PINS + spareBonus(frame);
        frame += 2;
      } else {
        score += sumOfRolls(frame);
        frame += 2;
      }
    }
    return score;
  }

  private boolean isStrike(int frame) {
    return rolls[frame] == TOTAL_PINS;
  }

  private boolean isSpare(int frame) {
    return sumOfRolls(frame) == TOTAL_PINS;
  }

  private int strikeBonus(int frame) {
    return sumOfRolls(frame + 1);
  }

  private int spareBonus(int frame) {
    return rolls[frame + 2];
  }

  private int sumOfRolls(int frame) {
    return rolls[frame] + rolls[frame + 1];
  }
}
