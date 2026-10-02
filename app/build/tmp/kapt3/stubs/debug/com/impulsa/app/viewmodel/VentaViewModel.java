package com.impulsa.app.viewmodel;

/**
 * Estado compartido por Dashboard, Registrar Venta, Historial, Detalle y Desempeño.
 * Toda la lógica de negocio (totales, comisión, progreso de meta) vive aquí,
 * nunca dentro de los Composable.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\nJt\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\n2\u0006\u0010\"\u001a\u00020\u001f2\b\u0010#\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u001f26\u0010%\u001a2\u0012\u0013\u0012\u00110\'\u00a2\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0013\u0012\u00110\'\u00a2\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020\u001d0&R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\f\u00a8\u0006,"}, d2 = {"Lcom/impulsa/app/viewmodel/VentaViewModel;", "Landroidx/lifecycle/ViewModel;", "repositorio", "Lcom/impulsa/app/data/VentaRepository;", "(Lcom/impulsa/app/data/VentaRepository;)V", "_estadoSincronizacion", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/impulsa/app/viewmodel/EstadoSincronizacion;", "comisionAcumulada", "Lkotlinx/coroutines/flow/StateFlow;", "", "getComisionAcumulada", "()Lkotlinx/coroutines/flow/StateFlow;", "estadoSincronizacion", "getEstadoSincronizacion", "totalVendido", "getTotalVendido", "ventas", "", "Lcom/impulsa/app/data/VentaEntity;", "getVentas", "obtenerPorId", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "progresoMeta", "", "metaMensual", "registrarVenta", "", "cliente", "", "concepto", "valor", "formaPago", "nota", "asesorCorreo", "alTerminar", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "exito", "sincronizada", "app_debug"})
public final class VentaViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.impulsa.app.data.VentaRepository repositorio = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.impulsa.app.data.VentaEntity>> ventas = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.impulsa.app.viewmodel.EstadoSincronizacion> _estadoSincronizacion = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.impulsa.app.viewmodel.EstadoSincronizacion> estadoSincronizacion = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Double> totalVendido = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Double> comisionAcumulada = null;
    
    public VentaViewModel(@org.jetbrains.annotations.NotNull()
    com.impulsa.app.data.VentaRepository repositorio) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.impulsa.app.data.VentaEntity>> getVentas() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.impulsa.app.viewmodel.EstadoSincronizacion> getEstadoSincronizacion() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Double> getTotalVendido() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Double> getComisionAcumulada() {
        return null;
    }
    
    public final void registrarVenta(@org.jetbrains.annotations.NotNull()
    java.lang.String cliente, @org.jetbrains.annotations.NotNull()
    java.lang.String concepto, double valor, @org.jetbrains.annotations.NotNull()
    java.lang.String formaPago, @org.jetbrains.annotations.Nullable()
    java.lang.String nota, @org.jetbrains.annotations.Nullable()
    java.lang.String asesorCorreo, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.Boolean, ? super java.lang.Boolean, kotlin.Unit> alTerminar) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object obtenerPorId(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.impulsa.app.data.VentaEntity> $completion) {
        return null;
    }
    
    public final float progresoMeta(double metaMensual) {
        return 0.0F;
    }
}