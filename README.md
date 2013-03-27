# Dieter Ember

Compile your handlebars templates into functions compatible with Ember.js.

## Usage

In your project.clj file

    [dieter/ember "0.4.0"]

Insert it into your ring middleware stack

```clojure
(use '[dieter :only [asset-pipeline]])
(require 'dieter.asset.ember)

(-> app
    (asset-pipeline config-options))
```

## License

Copyright (C) 2012 EdgeCase

Distributed under the Eclipse Public License, the same as Clojure.
