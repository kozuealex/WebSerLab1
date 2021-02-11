module x.serlab.fileutils {
    requires com.google.gson;
    exports x.serlab.fileutils;

    opens x.serlab.fileutils to com.google.gson;
}