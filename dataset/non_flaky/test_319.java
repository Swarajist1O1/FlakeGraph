class DummyClass_319 {
  @Test(timeout=10000)
  public void testIdempotent() {
    Object[][] procedures = {
        { Nfs3Constant.NFSPROC3.NULL, 1 },
        { Nfs3Constant.NFSPROC3.GETATTR, 1 },
        { Nfs3Constant.NFSPROC3.SETATTR, 1 },
        { Nfs3Constant.NFSPROC3.LOOKUP, 1 },
        { Nfs3Constant.NFSPROC3.ACCESS, 1 },
        { Nfs3Constant.NFSPROC3.READLINK, 1 },
        { Nfs3Constant.NFSPROC3.READ, 1 },
        { Nfs3Constant.NFSPROC3.WRITE, 1 },
        { Nfs3Constant.NFSPROC3.CREATE, 0 },
        { Nfs3Constant.NFSPROC3.MKDIR, 0 },
        { Nfs3Constant.NFSPROC3.SYMLINK, 0 },
        { Nfs3Constant.NFSPROC3.MKNOD, 0 },
        { Nfs3Constant.NFSPROC3.REMOVE, 0 },
        { Nfs3Constant.NFSPROC3.RMDIR, 0 },
        { Nfs3Constant.NFSPROC3.RENAME, 0 },
        { Nfs3Constant.NFSPROC3.LINK, 0 },
        { Nfs3Constant.NFSPROC3.READDIR, 1 },
        { Nfs3Constant.NFSPROC3.READDIRPLUS, 1 },
        { Nfs3Constant.NFSPROC3.FSSTAT, 1 },
        { Nfs3Constant.NFSPROC3.FSINFO, 1 },
        { Nfs3Constant.NFSPROC3.PATHCONF, 1 },
        { Nfs3Constant.NFSPROC3.COMMIT, 1 } };
    for (Object[] procedure : procedures) {
      boolean idempotent = procedure[1].equals(Integer.valueOf(1));
      Nfs3Constant.NFSPROC3 proc = (Nfs3Constant.NFSPROC3)procedure[0];
      if (idempotent) {
        Assert.assertTrue(("Procedure " + proc + " should be idempotent"),
            proc.isIdempotent());
      } else {
        Assert.assertFalse(("Procedure " + proc + " should be non-idempotent"),
            proc.isIdempotent());
      }
    }
  }

}