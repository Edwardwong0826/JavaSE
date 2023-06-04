package com.cool.test;

public class Solution {
     abstract static class Food {
        protected double proteins;
        protected double fats;
        protected double carbs;
        protected double tastyScore;

        public Food(double proteins, double fats, double carbs, double tastyScore){

            this.proteins = proteins;
            this.fats = fats;
            this.carbs = carbs;
            this.tastyScore = tastyScore;

        }

        @Override
        public String toString() {
            return "Food{" +
                    "proteins=" + proteins +
                    ", fats=" + fats +
                    ", carbs=" + carbs +
                    ", tastyScore=" + tastyScore +
                    '}';
        }

        public abstract void getMarcoNutrients();
    }

     static class Egg extends Food {

        String type;

        public Egg(double proteins, double fats, double carbs) {
            super(proteins, fats, carbs, 7);
            this.type = "non-vegaterian";
        }

        @Override
        public void getMarcoNutrients() {
            System.out.println("An egg has " + this.proteins + " gms of protein, " + this.fats + " gms of fats and " + this.carbs + " gms of carbonhydrates");
        }
    }

    public static void main(String[] args){
        Egg egg = new Egg(1.1, 2.3, 3.3);
        System.out.println(egg.toString());
        egg.getMarcoNutrients();


    }


}
