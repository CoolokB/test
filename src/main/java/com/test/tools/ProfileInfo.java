package com.test.tools;

import com.test.enums.Level;
import com.test.enums.TestType;

public class ProfileInfo {

   public static final String profile2Info;
   public static final String profile3Info;
   public static final String profile4Info;
   public static final String profile5Info;

   public static final String profile1Description;
   public static final String profile2Description;
   public static final String profile3Description;
   public static final String profile4Description;
   public static final String profile5Description;

    static {

        profile1Description = "повна відповідність особистих і професійних якостей вимогам ринку праці" + "\n" +
                "готовність розпочати роботу" + "\n" +
                "бажання працювати та активність у пошуку роботи" + "\n" +
                "наявність професії, на яку є попит на ринку праці" + "\n" +
                "наявність досвіду роботи вмінь та навичок" + "\n" +
                "відсутність досвіду довготривалого безробіття" + "\n" +
                "відсутність медичних протипоказаннь до роботи за наявною професією";

        profile2Description = "наявність бажання працювати та активність у пошуку роботи" + "\n" +
                "наявність навичок пошуку роботи, самопрезентації" + "\n" +
                "недостатній рівень кваліфікації" + "\n" +
                "відстутність професії" + "\n" +
                "наявність спеціальності, що не користується попитом на ринку праці" + "\n" +
                "відсутність стажу роботи";

        profile3Description = "низький рівень кваліфікації" + "\n" +
                "відсутність стажу роботи за спеціальністю (професією)" + "\n" +
                "відсутність навичок самопрезентації" + "\n" +
                "наявність значних труднощів в процесі пошуку роботи";

        profile4Description = "наявність професії, на яку є попит на ринку праці" + "\n" +
                "наявність досвіду роботи за фахом (професією), вмінь та навичок" + "\n" +
                "відсутність бажання працювати";

        profile5Description = "неготовність стати до праці" + "\n" +
                "відсутність професії(спеціальності) на ринку праці" + "\n" +
                "відсутність вмінь та активності у пошуку роботи" + "\n" +
                "наявність тривалої перерви в роботі" + "\n" +
                "відсутність досвіду роботи" + "\n" +
                "наявність частих змін місця роботи" + "\n" +
                "наявність звільнень за дисциплінарні порушення трудової дисципліни" + "\n" +
                "налаштованність на максимально довге перебування в статусі безробітного" + "\n" +
                "наявність серйозних проблем зі здоров'ям";

        profile2Info = "Високий рівень мотивації до працевлаштування" + "\n" + "та середній або низький потенціал працевлаштування";
        profile3Info = "Середній або низький рівень мотивації до працевлаштування" + "\n" + "та середній або низький потенціал працевлаштування";
        profile4Info = "Низький або середній рівень мотивації до працевлаштування " + "\n" + "та високий потенціал працевлаштування";
        profile5Info = "Низький рівень мотивації до працевлаштування " + "\n" + "та низький потенціал працевлаштування";

    }

    public static Level getLevel(int points, TestType type) {

        switch (type) {
            case MOTIVATION: {

                if (points < 14) return Level.LOW;
                if (points < 20) return Level.MEDIUM;
                return Level.HIGH;

            }

            case POTENTIAL: {

                if (points < 16) return Level.LOW;
                if (points < 26) return Level.MEDIUM;
                return Level.HIGH;
            }
        }

        return null;
    }

    public static int getProfile(int motivationTestResult,int potentialTestResult){

      boolean  motivationLow = motivationTestResult < 14;
      boolean  motivationMid = motivationTestResult > 13 && motivationTestResult < 20;
      boolean  motivationHigh = motivationTestResult > 19;
      boolean  potentialLow = potentialTestResult < 16;
      boolean  potentialMid = potentialTestResult > 15 && potentialTestResult < 26;
      boolean  potentialHigh = potentialTestResult > 25;

        if (motivationHigh && potentialHigh) {
            return 1;
        }
        if (motivationLow && potentialLow) {
            return  5;
        }

        if (motivationTestResult < 20 && potentialHigh) {
            return  4;
        }
        if (motivationLow || motivationMid && potentialLow || potentialMid && motivationMid) {
            return  3;
        }
        return 2;

    }

}