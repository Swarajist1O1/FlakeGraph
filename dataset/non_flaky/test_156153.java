class DummyClass_156153 {
  @Test
  public void testInvoke() throws IOException, ClassNotFoundException {

    // First generate a classfile with a MethodHnadle
    ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
    cv.visit(Opcodes.V1_7, Opcodes.ACC_PUBLIC, "UniformDistribution", null, Type.getInternalName(Object.class), null);

    MethodVisitor mv
        = cv.visitMethod(Opcodes.ACC_STATIC | Opcodes.ACC_PUBLIC, "sample", Type.getMethodDescriptor(Type.DOUBLE_TYPE,
            Type.getType(java.lang.invoke.MethodHandle.class) /* rng method */, Type.DOUBLE_TYPE /* max */), null, null);

    mv.visitCode();

    mv.visitVarInsn(Opcodes.ALOAD, 0); // load MethodHandle
    mv.visitInsn(Opcodes.ACONST_NULL); // null string... (just to test signatures with class names)


    // Call MethodHandle.invoke() with polymorphic signature: ()D
    mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, Type.getInternalName(java.lang.invoke.MethodHandle.class), "invoke",
        Type.getMethodDescriptor(Type.DOUBLE_TYPE, Type.getType(String.class)), false);

    mv.visitVarInsn(Opcodes.DLOAD, 1);
    mv.visitInsn(Opcodes.DMUL);
    mv.visitInsn(Opcodes.DRETURN);
    mv.visitEnd();
    cv.visitEnd();

    File tempDir = Files.createTempDir();
    File classFile = new File(tempDir, "UniformDistribution.class");
    Files.write(cv.toByteArray(), classFile);

    G.reset();

    String[] commandLine = { "-pp", "-cp", tempDir.getAbsolutePath(), "-O", "UniformDistribution" };

    System.out.println("Command Line: " + Arrays.toString(commandLine));

    Main.main(commandLine);
    validateClassFile("UniformDistribution");

  }

}