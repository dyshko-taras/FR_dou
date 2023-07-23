package com.netri.duo.tools;

import javax.swing.plaf.PanelUI;

public class Loc {

    public static final int PLAY = 0;
    public static final int SETTINGS = 1;
    public static final int ACHIEVEMENTS = 2;
    public static final int PLAY_10_TIMES = 3;
    public static final int GET_20_SCORE = 4;
    public static final int GET_60_SCORE = 5;
    public static final int SELECT_DIFFICULTY = 6;
    public static final int EASY = 7;
    public static final int HARD = 8;
    public static final int GAME_OVER = 9;
    public static final int SCORE = 10;
    public static final int PLAY_AGAIN = 11;

    public static final String[] EN = {
            "PLAY",
            "SETTING",
            "ACHIEVEMENTS",
            "PLAY 10 TIMES",
            "GET 20 SCORE",
            "GET 60 SCORE",
            "SELECT\n DIFFICULTY",
            "EASY",
            "HARD",
            "GAME OVER",
            "SCORE: ",
            "PLAY AGAIN",
    };

    public static final String[] BR = {
            "JOGUE",
            "CONFIGURAÇÃO",
            "ACHIEVEMENTOS",
            "JOGAR 10 VEZES",
            "OBTER 20 PONTOS",
            "OBTER 60 PONTOS",
            "SELECT\n DIFFICULTY",
            "FÁCIL",
            "HARD",
            "GANHO DE JOGO",
            "LUGAR: ",
            "JOGUE CONTINUA",
    };

    public static String[] currentLanguage = EN;

    public static void setLanguage(String languageCode) {
        if (languageCode.equals("br")) {
            currentLanguage = BR;
        } else if (languageCode.equals("en")) {
            currentLanguage = EN;
        }
    }

    public static String getLoc(int index) {
        if (index >= 0 && index < currentLanguage.length) {
            return currentLanguage[index];
        } else {
            return "Localized text not found";
        }
    }

}
