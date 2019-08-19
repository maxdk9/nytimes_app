package mazzy.and.nytimes_app.model;

public enum ArticleType {
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
    },
    FAVORITE {
        @Override
        public String getTypeString() {
            return "favorite";
        }
    };
    public abstract String  getTypeString();
}
