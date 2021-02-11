import x.serlab.plugin.ProductsHandler;
import x.serlab.spi.URLHandler;

module plugin {
    exports x.serlab.plugin;
    requires x.serlab.spi;
    provides URLHandler with ProductsHandler;
}