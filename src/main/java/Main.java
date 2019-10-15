import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;




class Fraction {
    double Numerator;
    double Denominator;


    void fractionPrint(){
        System.out.println("("  + Math.round(Numerator)  + "/" + Math.round(Denominator) + ") ");
    }

    double GetFractionValue(){
        if(Denominator != 0){
            return (Numerator/Denominator);
        }
        else {
            if (Numerator > 0){
                return Double.POSITIVE_INFINITY;
            } else if (Numerator == 0){
                return Double.NaN;
            } else {
                return Double.NEGATIVE_INFINITY;
            }
        }
    }

    void readFractionFromFile(){
        try(FileReader reader = new FileReader("samples.txt"))
        {
            int nmsm, dnmsm;
            while((nmsm = reader.read()) != -1){

                this.Numerator = nmsm;
                dnmsm = reader.read();
                this.Denominator = dnmsm;
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    int getRoundValue(){
        double value = this.GetFractionValue();
        return (int) value;
    }
}

class FractionSet {
    ArrayList<Fraction> FractionList = new ArrayList<Fraction>();

    void add(Fraction thisFraction)
    {
        FractionList.add(thisFraction);
    }

    void findMin(){
        if (!FractionList.isEmpty()) {
            int CurMin = 0;

            for (int i = 1; i < FractionList.size(); i++) {
                if (FractionList.get(i).GetFractionValue() < FractionList.get(CurMin).GetFractionValue()) {
                    CurMin = i;
                }
            }

            System.out.println("Minimal fraction in set is ");
            FractionList.get(CurMin).fractionPrint();
        } else{
            System.out.println("Set of fractions is empty!");
        }
    }

    void findMax(){
        if (!FractionList.isEmpty()) {
            int CurMax = 0;

            for (int i = 1; i < FractionList.size(); i++) {
                if (FractionList.get(i).GetFractionValue() > FractionList.get(CurMax).GetFractionValue()) {
                    CurMax = i;
                }
            }

            System.out.println("Maximal fraction in set is ");
            FractionList.get(CurMax).fractionPrint();
        } else{
            System.out.println("Set of fractions is empty!");
        }
    }

    int countLessThanThis(Fraction thisFraction) {
        int LCounter = 0;

        for (int i = 0; i < FractionList.size(); i++) {
            if (FractionList.get(i).GetFractionValue() < thisFraction.GetFractionValue()) {
                LCounter++;
            }
        }

        return LCounter;
    }

    int countGreaterThanThis(Fraction thisFraction) {
        int GCounter = 0;

        for (int i = 0; i < FractionList.size(); i++) {
            if (FractionList.get(i).GetFractionValue() > thisFraction.GetFractionValue()) {
                GCounter++;
            }
        }

        return GCounter;
    }
}

class FractionPolynominal {
    ArrayList<Fraction> FractionPolynom = new ArrayList<Fraction>();

    void addByFraction(Fraction thisFraction) {
        FractionPolynom.add(thisFraction);
    }

    void addByFractionSet(FractionSet thisFractionSet) {
        if (!thisFractionSet.FractionList.isEmpty()) {
            FractionPolynom.addAll(0, thisFractionSet.FractionList);
        } else {
            System.out.println("Set of fractions is empty!");
        }

    }

    long GCD(long a, long b) {
        while (a != 0 && b != 0) {
            if (a > b) {
                a %= b;
            } else {
                b %= a;
            }
        }
        return a + b;
    }

    void FractionPolynomSumm(FractionPolynominal firstFractionPolynominal, FractionPolynominal secondFractionPolynominal) {
        if (firstFractionPolynominal.FractionPolynom.size() > secondFractionPolynominal.FractionPolynom.size()) {
            for (int i = 0; i < secondFractionPolynominal.FractionPolynom.size(); i++) {

                double newDenominator = firstFractionPolynominal.FractionPolynom.get(i).Denominator * secondFractionPolynominal.FractionPolynom.get(i).Denominator;
                double newNumerator = firstFractionPolynominal.FractionPolynom.get(i).Numerator * secondFractionPolynominal.FractionPolynom.get(i).Denominator + secondFractionPolynominal.FractionPolynom.get(i).Numerator * firstFractionPolynominal.FractionPolynom.get(i).Denominator;

                long Divider = GCD(Math.round(newDenominator), Math.round(newNumerator));

                double finalDenominator = newDenominator / Divider;
                double finalNumerator = newNumerator / Divider;

                Fraction finalFraction = new Fraction();
                finalFraction.Numerator = finalNumerator;
                finalFraction.Numerator = finalDenominator;
                this.FractionPolynom.add(finalFraction);
            }
        } else {
            for (int i = 0; i < firstFractionPolynominal.FractionPolynom.size(); i++) {

                double newDenominator = firstFractionPolynominal.FractionPolynom.get(i).Denominator * secondFractionPolynominal.FractionPolynom.get(i).Denominator;
                double newNumerator = (firstFractionPolynominal.FractionPolynom.get(i).Numerator * secondFractionPolynominal.FractionPolynom.get(i).Denominator) + (secondFractionPolynominal.FractionPolynom.get(i).Numerator * firstFractionPolynominal.FractionPolynom.get(i).Denominator);

                System.out.println(newNumerator);
                System.out.println(newDenominator);

                long Divider = GCD(Math.round(newDenominator), Math.round(newNumerator));

                double finalDenominator = newDenominator / Divider;
                double finalNumerator = newNumerator / Divider;

                Fraction finalFraction = new Fraction();
                finalFraction.Numerator = finalNumerator;
                finalFraction.Denominator = finalDenominator;
                this.FractionPolynom.add(finalFraction);
            }

        }
    }

    double fractionPolinSumm(){
        double fps = 0;

        for (int i = 0; i < this.FractionPolynom.size(); i++) {
            fps += this.FractionPolynom.get(i).GetFractionValue();
        }

        return fps;
    }
}

public class Main {

    public static void main(String[] args) {
        Fraction fraction1 = new Fraction();
        Fraction fraction2 = new Fraction();
        Fraction fraction3 = new Fraction();
        Scanner input  = new Scanner(System.in);
        fraction1.Numerator = input.nextInt();
        fraction1.Denominator = input.nextInt();
        System.out.println(fraction1.GetFractionValue());
        FractionSet fractionSet1 = new FractionSet();
        fractionSet1.add(fraction1);
        fraction2.Numerator = input.nextInt();
        fraction2.Denominator = input.nextInt();
        System.out.println(fraction2.GetFractionValue());
        fractionSet1.add(fraction2);
        fraction3.Numerator = input.nextInt();
        fraction3.Denominator = input.nextInt();
        System.out.println(fraction3.GetFractionValue());
        fractionSet1.add(fraction3);
        fractionSet1.findMin();
        fractionSet1.findMax();
        System.out.println(fractionSet1.countLessThanThis(fraction3));
        System.out.println(fractionSet1.countGreaterThanThis(fraction1));
        FractionPolynominal FP1 = new FractionPolynominal();
        FractionPolynominal FP2 = new FractionPolynominal();
        FractionPolynominal FP3 = new FractionPolynominal();
        Fraction fraction4 = new Fraction();
        Fraction fraction5 = new Fraction();
        Fraction fraction6 = new Fraction();
        fraction4.Numerator = input.nextInt();
        fraction4.Denominator = input.nextInt();
        fraction5.Numerator = input.nextInt();
        fraction5.Denominator = input.nextInt();
        fraction6.Numerator = input.nextInt();
        fraction6.Denominator = input.nextInt();
        FP1.addByFractionSet(fractionSet1);
        FP2.addByFraction(fraction4);
        FP2.addByFraction(fraction5);
        FP2.addByFraction(fraction6);
        FP3.FractionPolynomSumm(FP1, FP2);
        System.out.println(FP3.FractionPolynom.size());

        for (int i = 0; i < FP3.FractionPolynom.size(); i++)
        {
            FP3.FractionPolynom.get(i).fractionPrint();
        }

        Fraction fraction7 = new Fraction();
        fraction7.readFractionFromFile();


        fraction7.fractionOutput();

        double FPS = FP3.fractionPolinSumm();
        System.out.println(FPS);

        input.close();
    }
}


