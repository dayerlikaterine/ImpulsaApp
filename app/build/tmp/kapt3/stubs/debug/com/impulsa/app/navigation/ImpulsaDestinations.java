package com.impulsa.app.navigation;

/**
 * Rutas de Navigation Component. Todas las pantallas de la app quedan listadas aquí.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0013"}, d2 = {"Lcom/impulsa/app/navigation/ImpulsaDestinations;", "", "()V", "CREDITOS", "", "DASHBOARD", "DESEMPENO", "DETALLE_VENTA", "HISTORIAL", "LOGIN", "PERFIL", "REGISTRAR_VENTA", "conBarraInferior", "", "getConBarraInferior", "()Ljava/util/Set;", "detalleVenta", "ventaId", "", "app_debug"})
public final class ImpulsaDestinations {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOGIN = "login";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DASHBOARD = "dashboard";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String REGISTRAR_VENTA = "registrar_venta";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String HISTORIAL = "historial";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DETALLE_VENTA = "detalle_venta/{ventaId}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DESEMPENO = "desempeno";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PERFIL = "perfil";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CREDITOS = "creditos";
    
    /**
     * Pantallas principales, con barra de navegación inferior visible.
     */
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Set<java.lang.String> conBarraInferior = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.impulsa.app.navigation.ImpulsaDestinations INSTANCE = null;
    
    private ImpulsaDestinations() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String detalleVenta(long ventaId) {
        return null;
    }
    
    /**
     * Pantallas principales, con barra de navegación inferior visible.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.lang.String> getConBarraInferior() {
        return null;
    }
}