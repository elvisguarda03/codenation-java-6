package challenge;

import java.util.regex.Pattern;

public class CriptografiaCesariana implements Criptografia {
    private final static Integer SHIFT = 3;
    private final static Integer LETTER_A = 65;
    private final static Integer LETTER_Z = 90;

    @Override
    public String criptografar(String textPlain) {
        validate(textPlain);
        return encryptOrDecrypt(textPlain, false);
    }

    @Override
    public String descriptografar(String textEncrypted) {
        validate(textEncrypted);
        return encryptOrDecrypt(textEncrypted, true);
    }

    private String encryptOrDecrypt(String textPlain, boolean isEncrypt) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < textPlain.length(); i++) {
            char character = textPlain.charAt(i);
            if (!isInclude(character)) {
                String encrypted = getStringByNumber(isEncrypt ? Character.toUpperCase(character) - SHIFT :
                        Character.toUpperCase(character) + SHIFT);
                sb.append(encrypted.toLowerCase());

                continue;
            }

            sb.append(character);
        }

        return sb.toString();
    }

    private void validate(String text) {
        if (text == null) {
            throw new NullPointerException("O texto precisa ser fornecido.");
        }
        if (text.trim().isEmpty()) {
            throw new IllegalArgumentException("O texto precisa ser fornecido.");
        }
    }


    private String getStringByNumber(int value) {
        if (value < LETTER_A) {
            int difference = LETTER_A - value - 1;
            return String.valueOf((char)(LETTER_Z - difference));
        }
        else if (value > LETTER_Z) {
            int difference = value - LETTER_Z - 1;
            return String.valueOf((char)(LETTER_A + difference));
        }

        return String.valueOf((char)value);
    }

    private boolean isInclude(Character character) {
        Pattern pattern = Pattern.compile("\\.|\\d+|\\s");
        return pattern.matcher(String.valueOf(character))
                .find();
    }
}