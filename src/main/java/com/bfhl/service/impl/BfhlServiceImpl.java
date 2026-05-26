package com.bfhl.service.impl;

import com.bfhl.dto.BfhlRequest;
import com.bfhl.dto.BfhlResponse;
import com.bfhl.service.BfhlService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    private static final String USER_ID = "prabhat_kumar_ahirwar_05122005";
    private static final String EMAIL = "prabhatkumar230564@acropolis.in";
    private static final String ROLL_NUMBER = "0827CS231181";

    @Override
    public BfhlResponse processData(BfhlRequest request) {
        BfhlResponse response = new BfhlResponse();
        response.setSuccess(true);
        response.setUserId(USER_ID);
        response.setEmail(EMAIL);
        response.setRollNumber(ROLL_NUMBER);

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        long sumOfNumbers = 0;

        List<String> inputData = request.getData();
        if (inputData != null) {
            for (String val : inputData) {
                if (val == null) continue;
                String trimmed = val.trim();
                if (trimmed.isEmpty()) continue;

                // Check if numeric
                if (isNumeric(trimmed)) {
                    long num = Long.parseLong(trimmed);
                    if (num % 2 == 0) {
                        evenNumbers.add(trimmed);
                    } else {
                        oddNumbers.add(trimmed);
                    }
                    sumOfNumbers += num;
                } 
                // Check if purely alphabetical
                else if (isAlphabetic(trimmed)) {
                    alphabets.add(trimmed.toUpperCase());
                } 
                // Otherwise it is a special character/string
                else {
                    specialCharacters.add(trimmed);
                }
            }
        }

        response.setOddNumbers(oddNumbers);
        response.setEvenNumbers(evenNumbers);
        response.setAlphabets(alphabets);
        response.setSpecialCharacters(specialCharacters);
        response.setSepcialCharacters(specialCharacters);
        response.setSum(String.valueOf(sumOfNumbers));
        response.setConcatString(generateConcatString(inputData));

        return response;
    }

    private boolean isNumeric(String str) {
        return str.matches("^-?\\d+$");
    }

    private boolean isAlphabetic(String str) {
        return str.matches("^[a-zA-Z]+$");
    }

    private String generateConcatString(List<String> data) {
        if (data == null) {
            return "";
        }
        
        // 1. Take all alphabetical characters
        StringBuilder sb = new StringBuilder();
        for (String s : data) {
            if (s != null) {
                for (char c : s.toCharArray()) {
                    if (Character.isLetter(c)) {
                        sb.append(c);
                    }
                }
            }
        }
        
        // 2. Reverse order
        String reversed = sb.reverse().toString();
        
        // 3. Alternating caps (starting with UPPERCASE)
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            if (i % 2 == 0) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(Character.toLowerCase(c));
            }
        }
        
        return result.toString();
    }
}
