package com.impulsa.app.data;

/**
 * Punto único desde el que el ViewModel lee y escribe ventas.
 * Room es la fuente de verdad local; Firebase es solo un respaldo en línea.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\r\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\u0010J\"\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0086@\u00a2\u0006\u0002\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0017"}, d2 = {"Lcom/impulsa/app/data/VentaRepository;", "", "dao", "Lcom/impulsa/app/data/VentaDao;", "servicioFirebase", "Lcom/impulsa/app/remote/FirebaseVentaService;", "(Lcom/impulsa/app/data/VentaDao;Lcom/impulsa/app/remote/FirebaseVentaService;)V", "ventas", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/impulsa/app/data/VentaEntity;", "getVentas", "()Lkotlinx/coroutines/flow/Flow;", "obtenerPorId", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registrarVenta", "", "venta", "asesorCorreo", "", "(Lcom/impulsa/app/data/VentaEntity;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class VentaRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.impulsa.app.data.VentaDao dao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.impulsa.app.remote.FirebaseVentaService servicioFirebase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.impulsa.app.data.VentaEntity>> ventas = null;
    
    public VentaRepository(@org.jetbrains.annotations.NotNull()
    com.impulsa.app.data.VentaDao dao, @org.jetbrains.annotations.NotNull()
    com.impulsa.app.remote.FirebaseVentaService servicioFirebase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.impulsa.app.data.VentaEntity>> getVentas() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object obtenerPorId(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.impulsa.app.data.VentaEntity> $completion) {
        return null;
    }
    
    /**
     * Guarda la venta en Room y luego intenta sincronizarla con Firestore.
     * Devuelve true si además quedó sincronizada en línea.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object registrarVenta(@org.jetbrains.annotations.NotNull()
    com.impulsa.app.data.VentaEntity venta, @org.jetbrains.annotations.Nullable()
    java.lang.String asesorCorreo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
}