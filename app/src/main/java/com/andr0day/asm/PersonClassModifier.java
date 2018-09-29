package com.andr0day.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class PersonClassModifier {

    public void modify(byte[] classBytes) {
        try {
            ClassReader classReader = new ClassReader(classBytes);
            ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
            ClassVisitor changeVisitor = new ChangeVisitor(classWriter);
            classReader.accept(changeVisitor, ClassReader.EXPAND_FRAMES);
            try {
                OutputStream outputStream = new FileOutputStream("Person2.class");
                outputStream.write(classWriter.toByteArray());
                outputStream.close();
            } catch (Exception e) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ChangeVisitor extends ClassVisitor {

        ChangeVisitor(ClassVisitor cv) {
            super(Opcodes.ASM6, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
            if (name.equals("showInfo")) {
                return new RedefineAdvice(mv, access, name, desc);
            }
            return mv;
        }
    }

    class RedefineAdvice extends AdviceAdapter {

        protected RedefineAdvice(MethodVisitor mv, int access, String name, String desc) {
            super(Opcodes.ASM6, mv, access, name, desc);
        }

        @Override
        protected void onMethodEnter() {
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
            super.onMethodEnter();
        }

        protected void onMethodExit(final int opcode) {
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
            super.onMethodExit(opcode);
        }

    }
}
