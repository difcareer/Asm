package com.andr0day.asm.test;

@SuppressWarnings("nochecked")
public class Outer {
    private int outerF = 0;

    public void outerM() {
        System.out.println("outerM");
    }

    public static class Inner extends AbsInterf {
        private int innerF = 0;

        @Override
        public void nice() {
            System.out.println("nice");
        }

        public void innerM() {
            System.out.println("innerM");
        }
    }
}
