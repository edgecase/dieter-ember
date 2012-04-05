# Dieter Ember

Compile your handlebars templates into functions compatible with Ember.js.

    [dieter/ember "0.1.0"]

Insert it into your ring middleware stack

```clojure
(use 'dieter)
(require 'dieter.ember)
(-> app
    (asset-pipeline config-options))
```

## License

Copyright (C) 2012 EdgeCase

Distributed under the Eclipse Public License, the same as Clojure.
