package patientintake;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorShould {

    @Test
    @DisplayName("calculates BMI to two places correctly via height and weight")
    public void calculateBmiCorrectly(){
        assertEquals(19.2, BMICalculator.calculateBmi(69,130));
        assertEquals(21.52, BMICalculator.calculateBmi(70,150));
    }

}