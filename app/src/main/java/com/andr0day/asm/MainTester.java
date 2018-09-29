package com.andr0day.asm;

import com.andr0day.asm.test.Outer;

import org.objectweb.asm.ClassReader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainTester {

    public static void main(String[] args) {
        try {
            ClassVisitorPrinter printer = new ClassVisitorPrinter();

            ClassReader cr = new ClassReader(Outer.class.getName());
            cr.accept(printer, 0);

            cr = new ClassReader(Outer.Inner.class.getName());
            cr.accept(printer, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("ClassVisitorPrinter done");

        PersonClassGenerator personClassGenerator = new PersonClassGenerator();
        byte[] classBytes = personClassGenerator.generate();

        try {
            FileOutputStream out = new FileOutputStream("Person.class");
            out.write(classBytes);
            out.close();
        } catch (Exception e) {
        }

        try {
            Class personClazz = new MyClassLoader().defineClass("com.andr0day.ASMTest.Person", classBytes);
            Object personObj = personClazz.newInstance();
            Field nameField = personClazz.getDeclaredField("name");
            Field ageField = personClazz.getDeclaredField("age");
            Method setNameMethod = personClazz.getDeclaredMethod("setName", String.class);
            setNameMethod.setAccessible(true);
            setNameMethod.invoke(personObj, "andr0day");

            Method setAgeMethod = personClazz.getDeclaredMethod("setAge", int.class);
            setAgeMethod.setAccessible(true);
            setAgeMethod.invoke(personObj, 18);

            Method showInfoMethod = personClazz.getDeclaredMethod("showInfo");
            showInfoMethod.setAccessible(true);
            showInfoMethod.invoke(personObj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("PersonClassGenerator done");

        PersonClassModifier personClassModifier = new PersonClassModifier();
        try {
            personClassModifier.modify(classBytes);
        } catch (Exception e) {

        }

        System.out.println("PersonClassModifier done");
    }
}
