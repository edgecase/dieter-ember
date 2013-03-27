// provide a global exports for Ember to attach its public functions to
var exports = {};

function formatError(e, filename) {
  return filename + ": " + e.message;
}

function compileEmberHandlebarsTemplate(input, absolutePath, filename){
  try {
    var filenameMatch = filename.match(/\/([^\/]+?)\.(?:hbs|handlebars)/);
    var resultWrapper = "Ember.TEMPLATES['" + filenameMatch[1] +"']=Ember.Handlebars.template(";
    var compiledTemplate = exports.precompile(input);
    return resultWrapper + compiledTemplate + ");";
  } catch (x) {
    return formatError(x, filename);
  }
}
