package defpackage;

/* loaded from: classes.dex */
public class LogConfiguration {
    public boolean isLogEnable;
    public boolean isScreenMonitorEnable;

    /* loaded from: classes.dex */
    public static final class configurator {

        /* renamed from: Ll1lLl1l1LL1l1Ll  reason: collision with root package name */
        public boolean isLogEnable;

        /* renamed from: lilil1liLi1lLL1l1l  reason: collision with root package name */
        public boolean isScreenMonitorEnable;
    }

    public LogConfiguration(configurator configurator) {
        this.isLogEnable = configurator.isLogEnable;
        this.isScreenMonitorEnable = configurator.isScreenMonitorEnable;
    }

    public static configurator newBuilder() {
        return new configurator();
    }

    public boolean isLogEnable() {
        return this.isLogEnable;
    }

    public boolean isScreenMonitorEnable() {
        return this.isScreenMonitorEnable;
    }
}