package sql;

import sql.service.EleviService;

public class ApplicationContext {

    private static ApplicationContext INSTANCE;

    private EleviService eleviService;

    private ApplicationContext() {

    }

    private void load() {
        this.eleviService = new EleviService();
    }

    private static ApplicationContext getInstance() {
        return INSTANCE;
    }

}
