class DummyClass_162557 {
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
    public void complete(int status) {
      complete(() -> status, null);
    }

}