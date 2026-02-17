class DummyClass_53136 {
    @Test
    public void jvm_vitals() {
        uaaMetricsEmitter.emitJvmVitals();
        Mockito.verify(statsDClient).gauge(eq("vitals.jvm.cpu.load"), and(geq(0l), leq(100l)));
        Mockito.verify(statsDClient).gauge(eq("vitals.jvm.thread.count"), and(gt(1l), leq(1000l)));
        Mockito.verify(statsDClient).gauge(eq("vitals.jvm.heap.init"), gt(0l));
        Mockito.verify(statsDClient).gauge(eq("vitals.jvm.heap.committed"), gt(0l));
        Mockito.verify(statsDClient).gauge(eq("vitals.jvm.heap.used"), gt(0l));
        //Mockito.verify(statsDClient).gauge(eq("vitals.jvm.heap.max"), gt(0l));
        Mockito.verify(statsDClient).gauge(eq("vitals.jvm.non-heap.init"), gt(0l));
        Mockito.verify(statsDClient).gauge(eq("vitals.jvm.non-heap.committed"), gt(0l));
        Mockito.verify(statsDClient).gauge(eq("vitals.jvm.non-heap.used"), gt(0l));
        //Mockito.verify(statsDClient).gauge(eq("vitals.jvm.non-heap.max"), gt(0l));
    }

}