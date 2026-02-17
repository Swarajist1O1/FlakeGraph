class DummyClass_156152 {
  @Test
  public void testConstant() throws Throwable {

    // First generate a classfile with a MethodHnadle
    ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
    cv.visit(Opcodes.V1_7, Opcodes.ACC_PUBLIC, "HelloMethodHandles", null, Type.getInternalName(Object.class), null);
    MethodVisitor mv = cv.visitMethod(Opcodes.ACC_STATIC | Opcodes.ACC_PUBLIC, "getSquareRoot",
        Type.getMethodDescriptor(Type.getType(java.lang.invoke.MethodHandle.class)), null, null);

    mv.visitCode();

    mv.visitLdcInsn(new Handle(Opcodes.H_INVOKESTATIC, Type.getInternalName(Math.class), "sqrt",
        Type.getMethodDescriptor(Type.DOUBLE_TYPE, Type.DOUBLE_TYPE), false));

    mv.visitInsn(Opcodes.ARETURN);
    mv.visitEnd();

    cv.visitEnd();

    File tempDir = Files.createTempDir();
    File classFile = new File(tempDir, "HelloMethodHandles.class");
    Files.write(cv.toByteArray(), classFile);

    G.reset();

    String[] commandLine = { "-pp", "-cp", tempDir.getAbsolutePath(), "-O", "HelloMethodHandles", };

    System.out.println("Command Line: " + Arrays.toString(commandLine));

    Main.main(commandLine);

    Class<?> clazz = validateClassFile("HelloMethodHandles");
    java.lang.invoke.MethodHandle methodHandle
        = (java.lang.invoke.MethodHandle) clazz.getMethod("getSquareRoot").invoke(null);

    assertThat((Double) methodHandle.invoke(16.0), equalTo(4.0));
  }

}