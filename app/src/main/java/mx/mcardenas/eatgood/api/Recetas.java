package mx.mcardenas.eatgood.api;

public class Recetas{
    public int key;
    public boolean more;
    public int page;
    public String cn;
    public  String i;
    public int k;
    public boolean m;
    public String mt;
    public String n;
    public String pa;
    public float pr;
    public String t;
    public int v;
    public float vh;
    public int vp;
    public float vr;
    public String x;

    public Recetas (int key, boolean more, int page, String cn, String i, int k, boolean m, String mt, String n, String pa, float pr, String t, int v, float vh, int vp,float vr,
                    String x){
        this.key = key;
        this.more = more;
        this.page = page;
        this.cn = cn;
        this.i = i;
        this.k = k;
        this.m = m;
        this.mt = mt;
        this.n = n;
        this.pa = pa;
        this.pr = pr;
        this.t = t;
        this.v = v;
        this.vh = vh;
        this.vp = vp;
        this.vr = vr;
        this.x = x;
    }
}
