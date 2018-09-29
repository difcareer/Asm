package com.andr0day.asm;

import org.objectweb.asm.*;

import java.util.Arrays;

public class ClassVisitorPrinter extends ClassVisitor {
    public ClassVisitorPrinter() {
        super(Opcodes.ASM6);
    }

    public void visitSource(final String source, final String debug) {
        System.out.println("visitSource, source:" + source + ", debug:" + debug);
        super.visitSource(source, debug);
    }

    public ModuleVisitor visitModule(final String name, final int access, final String version) {
        System.out.println("visitModule, name:" + name + ", access:" + access + ", version:" + version);
        return super.visitModule(name, access, version);
    }

    public void visitNestHostExperimental(final String nestHost) {
        System.out.println("visitNestHostExperimental, nestHost:" + nestHost);
        super.visitNestHostExperimental(nestHost);
    }

    public void visitOuterClass(final String owner, final String name, final String descriptor) {
        System.out.println("visitOuterClass, owner:" + owner + ", name:" + name + ", descriptor" + descriptor);
        super.visitOuterClass(owner, name, descriptor);
    }

    public AnnotationVisitor visitAnnotation(final String descriptor, final boolean visible) {
        System.out.println("visitAnnotation");
        return super.visitAnnotation(descriptor, visible);
    }

    public AnnotationVisitor visitTypeAnnotation(
            final int typeRef, final TypePath typePath, final String descriptor, final boolean visible) {
        System.out.println("visitTypeAnnotation, typeRe:" + typeRef + ", typePath:" + typePath + ", descriptor:" + descriptor + ", visible:" + visible);
        return super.visitTypeAnnotation(typeRef, typePath, descriptor, visible);
    }

    public void visitAttribute(final Attribute attribute) {
        System.out.println("visitAttribute, attribute:" + attribute);
        super.visitAttribute(attribute);
    }

    public void visitNestMemberExperimental(final String nestMember) {
        System.out.println("visitNestMemberExperimental, nestMember:" + nestMember);
        super.visitNestMemberExperimental(nestMember);
    }

    public void visitInnerClass(
            final String name, final String outerName, final String innerName, final int access) {
        System.out.println("visitInnerClass, name:" + name + ", outerName:" + outerName + ", innerName:" + innerName + ", access:" + access);
        super.visitInnerClass(name, outerName, innerName, access);
    }

    public FieldVisitor visitField(
            final int access,
            final String name,
            final String descriptor,
            final String signature,
            final Object value) {
        System.out.println("visitField, access:" + access + ", name:" + name + ", descriptor:" + descriptor + ", signature:" + signature + ", value:" + value);
        return super.visitField(access, name, descriptor, signature, value);
    }

    public MethodVisitor visitMethod(
            final int access,
            final String name,
            final String descriptor,
            final String signature,
            final String[] exceptions) {
        System.out.println("visitMethod, access:" + access + ", name:" + name + ", descriptor:" + descriptor + ", signature:" + signature + ", exceptions:" + Arrays.toString(exceptions));
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    public void visitEnd() {
        System.out.println("visitEnd");
        super.visitEnd();
    }
}
