class DummyClass_162613 {
  @Test
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (!(obj instanceof MethodSignature)) {
        return false;
      }
      MethodSignature other = (MethodSignature) obj;
      return Objects.equals(name, other.name)
          && Objects.equals(parameterTypes, other.parameterTypes)
          && Objects.equals(returnType, other.returnType);
    }

}