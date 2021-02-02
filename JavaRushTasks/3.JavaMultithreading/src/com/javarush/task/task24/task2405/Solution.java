package com.javarush.task.task24.task2405;

/* 
Black box
*/

public class Solution implements Action {
    public static int countActionObjects;

    public int param;

   // private Action solutionAction = null;

    /*public Solution(int param) {
        this.param = param;
        if (solutionAction == null){
            solutionAction = new Action() {



                FirstClass fc = new FirstClass(){
                    @Override
                    public Action getDependantAction(){return new Action(){
                        @Override
                        public void someAction(){
                            SecondClass innerClass = new SecondClass();
                            innerClass.someAction();
                            System.out.println(innerClass.SPECIFIC_ACTION_FOR_ANONYMOUS_SECOND_CLASS_PARAM + Solution.this.param);
                        };
                    };};
                } ;


                public void someAction() {
                    for(;Solution.this.param > 1; Solution.this.param--){
                        System.out.println(Solution.this.param);
                    }
                    if (Solution.this.param > 0){
                        System.out.println(Solution.this.param);
                        Solution.this.param--;
                        fc.someAction();
                        fc.getDependantAction().someAction();
                    } else {
                        SecondClass sc = new SecondClass();
                        sc.someAction();
                        System.out.println(sc.SPECIFIC_ACTION_FOR_ANONYMOUS_SECOND_CLASS_PARAM + param);
                    }
                }
            };
        }
    }*/

        private Action solutionAction = new Action() {

            public void someAction() {


                for (; param > 1; param--) {
                    System.out.println(param);
                }
                if (param > 0) {
                    System.out.println(param);
                    param--;

                    FirstClass fc = new FirstClass() {

                        @Override
                        public Action getDependantAction() {
                            super.someAction();
                            return new Action() {

                                @Override
                                public void someAction() {

                                    SecondClass innerClass = new SecondClass();
                                    //innerClass.someAction();
                                    //System.out.println(innerClass.sb);
                                    //System.out.println(innerClass.SPECIFIC_ACTION_FOR_ANONYMOUS_SECOND_CLASS_PARAM + param);
                                    System.out.println(innerClass.sb.append(innerClass.SPECIFIC_ACTION_FOR_ANONYMOUS_SECOND_CLASS_PARAM).append(param));
                                }
                            };
                        }
                    };
                    //fc.someAction();
                    fc.getDependantAction().someAction();
                } else {
                    SecondClass sc = new SecondClass();
                    //sc.someAction();
                    //System.out.println(sc.sb);
                    //System.out.println(sc.SPECIFIC_ACTION_FOR_ANONYMOUS_SECOND_CLASS_PARAM + param);
                    System.out.println(sc.sb.append(sc.SPECIFIC_ACTION_FOR_ANONYMOUS_SECOND_CLASS_PARAM).append(param));
                }
            }


        };


    public Solution(int param) {
        this.param = param;
    }

    @Override
    public void someAction() {
        solutionAction.someAction();
    }

    /**
     * 5
     * 4
     * 3
     * 2
     * 1
     * class FirstClass, method someAction
     * class SecondClass, method someAction
     * Specific action for anonymous SecondClass, param = 0
     * Count of created Action objects is 2
     * class SecondClass, method someAction
     * Specific action for anonymous SecondClass, param = -1
     * Count of created Action objects is 3
     */
    public static void main(String[] args) {
        Solution solution = new Solution(5);
        solution.someAction();
        System.out.println("Count of created Action objects is " + countActionObjects);

        solution = new Solution(-1);
        solution.someAction();
        System.out.println("Count of created Action objects is " + countActionObjects);
    }
}
