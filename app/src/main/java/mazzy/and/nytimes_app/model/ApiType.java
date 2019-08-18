package mazzy.and.nytimes_app.model;

public enum ApiType {
    EMAILED{
        @Override
        public String getTypeString() {
            return "emailed";
        }
    },
    SHARED {
        @Override
        public String getTypeString() {
            return "shared";
        }
    },
    VIEWED {
        @Override
        public String getTypeString() {
            return "viewed";
        }
    };
    public abstract String  getTypeString();
}
