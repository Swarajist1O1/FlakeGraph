class DummyClass_53133 {
    @Test
    public void vm_vitals() {
        uaaMetricsEmitter.emitVmVitals();
        Mockito.verify(statsDClient).gauge(eq("vitals.vm.cpu.count"), gt(0l));
        Mockito.verify(statsDClient).gauge(eq("vitals.vm.cpu.load"), geq(0l));
        Mockito.verify(statsDClient).gauge(eq("vitals.vm.memory.total"), geq(134217728l));
        Mockito.verify(statsDClient).gauge(eq("vitals.vm.memory.committed"), geq(1l));
        Mockito.verify(statsDClient).gauge(eq("vitals.vm.memory.free"), geq(1l));
    }

}