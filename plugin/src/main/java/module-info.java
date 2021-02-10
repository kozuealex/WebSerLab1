import x.serlab.plugin.ExternalPage;
import x.serlab.plugin.SwedishPage;
import x.serlab.spi.Page;

module plugin {
    requires x.serlab.spi;
    provides Page with ExternalPage, SwedishPage;
}