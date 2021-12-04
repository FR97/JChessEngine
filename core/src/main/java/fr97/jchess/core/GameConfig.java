package fr97.jchess.core;

import fr97.jchess.core.ai.AiStrategy;

public class GameConfig {

    private final boolean isAi;
    private final AiStrategy strategy;
    private final int depth;

    private GameConfig(boolean isAi, AiStrategy strategy, int depth) {
        this.isAi = isAi;
        this.strategy = strategy;
        this.depth = depth;
    }

    public static GameConfig createAiConfig(AiStrategy strategy, int depth) {
        return new GameConfig(true, strategy, depth);
    }

    public static GameConfig createHumanConfig() {
        return new GameConfig(false, null, 0);
    }

    public boolean isAi() {
        return isAi;
    }

    public AiStrategy getStrategy() {
        return strategy;
    }

    public int getDepth() {
        return depth;
    }

    public boolean isValid() {
        boolean hasAiAndNoStrategy = isAi && strategy == null;
        boolean validDepth = depth < 8;
        boolean hasDepthAndIsHuman = !isAi && depth > 0;

        if (!hasAiAndNoStrategy && validDepth && !hasDepthAndIsHuman) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        if (isAi) {
            String strDepth = String.valueOf(this.depth);
            if (strategy != null) {
                return "Ai [" + strategy.toString() + ", " + strDepth + "]";
            } else {
                return "Ai [ Error No Strategy " + ", " + strDepth + "]";
            }
        }
        return "Human";
    }


}

