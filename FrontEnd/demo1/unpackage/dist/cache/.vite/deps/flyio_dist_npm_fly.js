var __getOwnPropNames = Object.getOwnPropertyNames;
var __commonJS = (cb, mod) => function __require() {
  return mod || (0, cb[__getOwnPropNames(cb)[0]])((mod = { exports: {} }).exports, mod), mod.exports;
};

// ../../../../../../Users/moqi/Desktop/作业/大二小学期/2024年Java程序设计实践 (2)/mjymfdlxlszwxx-sweethome/FrontEnd/demo1/node_modules/flyio/dist/npm/fly.js
var require_fly = __commonJS({
  "../../../../../../Users/moqi/Desktop/作业/大二小学期/2024年Java程序设计实践 (2)/mjymfdlxlszwxx-sweethome/FrontEnd/demo1/node_modules/flyio/dist/npm/fly.js"(exports, module) {
    (function webpackUniversalModuleDefinition(root, factory) {
      if (typeof exports === "object" && typeof module === "object")
        module.exports = factory();
      else if (typeof define === "function" && define.amd)
        define([], factory);
      else {
        var a = factory();
        for (var i in a)
          (typeof exports === "object" ? exports : root)[i] = a[i];
      }
    })(exports, function() {
      return (
        /******/
        function(modules) {
          var installedModules = {};
          function __webpack_require__(moduleId) {
            if (installedModules[moduleId]) {
              return installedModules[moduleId].exports;
            }
            var module2 = installedModules[moduleId] = {
              /******/
              i: moduleId,
              /******/
              l: false,
              /******/
              exports: {}
              /******/
            };
            modules[moduleId].call(module2.exports, module2, module2.exports, __webpack_require__);
            module2.l = true;
            return module2.exports;
          }
          __webpack_require__.m = modules;
          __webpack_require__.c = installedModules;
          __webpack_require__.i = function(value) {
            return value;
          };
          __webpack_require__.d = function(exports2, name, getter) {
            if (!__webpack_require__.o(exports2, name)) {
              Object.defineProperty(exports2, name, {
                /******/
                configurable: false,
                /******/
                enumerable: true,
                /******/
                get: getter
                /******/
              });
            }
          };
          __webpack_require__.n = function(module2) {
            var getter = module2 && module2.__esModule ? (
              /******/
              function getDefault() {
                return module2["default"];
              }
            ) : (
              /******/
              function getModuleExports() {
                return module2;
              }
            );
            __webpack_require__.d(getter, "a", getter);
            return getter;
          };
          __webpack_require__.o = function(object, property) {
            return Object.prototype.hasOwnProperty.call(object, property);
          };
          __webpack_require__.p = "";
          return __webpack_require__(__webpack_require__.s = 2);
        }([
          /* 0 */
          /***/
          function(module2, exports2, __webpack_require__) {
            "use strict";
            var _typeof = typeof Symbol === "function" && typeof Symbol.iterator === "symbol" ? function(obj) {
              return typeof obj;
            } : function(obj) {
              return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj;
            };
            module2.exports = {
              type: function type(ob) {
                return Object.prototype.toString.call(ob).slice(8, -1).toLowerCase();
              },
              isObject: function isObject(ob, real) {
                if (real) {
                  return this.type(ob) === "object";
                } else {
                  return ob && (typeof ob === "undefined" ? "undefined" : _typeof(ob)) === "object";
                }
              },
              isFormData: function isFormData(val) {
                return typeof FormData !== "undefined" && val instanceof FormData;
              },
              trim: function trim(str) {
                return str.replace(/(^\s*)|(\s*$)/g, "");
              },
              encode: function encode(val) {
                return encodeURIComponent(val).replace(/%40/gi, "@").replace(/%3A/gi, ":").replace(/%24/g, "$").replace(/%2C/gi, ",").replace(/%20/g, "+").replace(/%5B/gi, "[").replace(/%5D/gi, "]");
              },
              formatParams: function formatParams(data) {
                var str = "";
                var first = true;
                var that = this;
                if (!this.isObject(data)) {
                  return data;
                }
                function _encode(sub, path) {
                  var encode = that.encode;
                  var type = that.type(sub);
                  if (type == "array") {
                    sub.forEach(function(e, i) {
                      if (!that.isObject(e))
                        i = "";
                      _encode(e, path + ("%5B" + i + "%5D"));
                    });
                  } else if (type == "object") {
                    for (var key in sub) {
                      if (path) {
                        _encode(sub[key], path + "%5B" + encode(key) + "%5D");
                      } else {
                        _encode(sub[key], encode(key));
                      }
                    }
                  } else {
                    if (!first) {
                      str += "&";
                    }
                    first = false;
                    str += path + "=" + encode(sub);
                  }
                }
                _encode(data, "");
                return str;
              },
              // Do not overwrite existing attributes
              merge: function merge(a, b) {
                for (var key in b) {
                  if (!a.hasOwnProperty(key)) {
                    a[key] = b[key];
                  } else if (this.isObject(b[key], 1) && this.isObject(a[key], 1)) {
                    this.merge(a[key], b[key]);
                  }
                }
                return a;
              }
            };
          },
          ,
          /* 2 */
          /***/
          function(module2, exports2, __webpack_require__) {
            function KEEP(_, cb) {
              cb();
            }
            "use strict";
            var _createClass = /* @__PURE__ */ function() {
              function defineProperties(target, props) {
                for (var i = 0; i < props.length; i++) {
                  var descriptor = props[i];
                  descriptor.enumerable = descriptor.enumerable || false;
                  descriptor.configurable = true;
                  if ("value" in descriptor)
                    descriptor.writable = true;
                  Object.defineProperty(target, descriptor.key, descriptor);
                }
              }
              return function(Constructor, protoProps, staticProps) {
                if (protoProps)
                  defineProperties(Constructor.prototype, protoProps);
                if (staticProps)
                  defineProperties(Constructor, staticProps);
                return Constructor;
              };
            }();
            function _classCallCheck(instance, Constructor) {
              if (!(instance instanceof Constructor)) {
                throw new TypeError("Cannot call a class as a function");
              }
            }
            var utils = __webpack_require__(0);
            var isBrowser = typeof document !== "undefined";
            var Fly = function() {
              function Fly2(engine) {
                _classCallCheck(this, Fly2);
                this.engine = engine || XMLHttpRequest;
                this.default = this;
                function wrap(interceptor) {
                  var resolve = void 0;
                  var reject = void 0;
                  function _clear() {
                    interceptor.p = resolve = reject = null;
                  }
                  utils.merge(interceptor, {
                    lock: function lock() {
                      if (!resolve) {
                        interceptor.p = new Promise(function(_resolve, _reject) {
                          resolve = _resolve;
                          reject = _reject;
                        });
                      }
                    },
                    unlock: function unlock() {
                      if (resolve) {
                        resolve();
                        _clear();
                      }
                    },
                    clear: function clear() {
                      if (reject) {
                        reject("cancel");
                        _clear();
                      }
                    }
                  });
                }
                var interceptors = this.interceptors = {
                  response: {
                    use: function use(handler, onerror) {
                      this.handler = handler;
                      this.onerror = onerror;
                    }
                  },
                  request: {
                    use: function use(handler) {
                      this.handler = handler;
                    }
                  }
                };
                var irq = interceptors.request;
                var irp = interceptors.response;
                wrap(irp);
                wrap(irq);
                this.config = {
                  method: "GET",
                  baseURL: "",
                  headers: {},
                  timeout: 0,
                  params: {},
                  // Default Url params
                  parseJson: true,
                  // Convert response data to JSON object automatically.
                  withCredentials: false
                };
              }
              _createClass(Fly2, [{
                key: "request",
                value: function request(url, data, options) {
                  var _this = this;
                  var engine = new this.engine();
                  var contentType = "Content-Type";
                  var contentTypeLowerCase = contentType.toLowerCase();
                  var interceptors = this.interceptors;
                  var requestInterceptor = interceptors.request;
                  var responseInterceptor = interceptors.response;
                  var requestInterceptorHandler = requestInterceptor.handler;
                  var promise = new Promise(function(resolve, reject) {
                    if (utils.isObject(url)) {
                      options = url;
                      url = options.url;
                    }
                    options = options || {};
                    options.headers = options.headers || {};
                    function isPromise(p) {
                      return p && p.then && p.catch;
                    }
                    function enqueueIfLocked(promise2, callback) {
                      if (promise2) {
                        promise2.then(function() {
                          callback();
                        });
                      } else {
                        callback();
                      }
                    }
                    function makeRequest(options2) {
                      data = options2.body;
                      url = utils.trim(options2.url);
                      var baseUrl = utils.trim(options2.baseURL || "");
                      if (!url && isBrowser && !baseUrl)
                        url = location.href;
                      if (url.indexOf("http") !== 0) {
                        var isAbsolute = url[0] === "/";
                        if (!baseUrl && isBrowser) {
                          var arr = location.pathname.split("/");
                          arr.pop();
                          baseUrl = location.protocol + "//" + location.host + (isAbsolute ? "" : arr.join("/"));
                        }
                        if (baseUrl[baseUrl.length - 1] !== "/") {
                          baseUrl += "/";
                        }
                        url = baseUrl + (isAbsolute ? url.substr(1) : url);
                        if (isBrowser) {
                          var t = document.createElement("a");
                          t.href = url;
                          url = t.href;
                        }
                      }
                      var responseType = utils.trim(options2.responseType || "");
                      var needQuery = ["GET", "HEAD", "DELETE", "OPTION"].indexOf(options2.method) !== -1;
                      var dataType = utils.type(data);
                      var params = options2.params || {};
                      if (needQuery && dataType === "object") {
                        params = utils.merge(data, params);
                      }
                      params = utils.formatParams(params);
                      var _params = [];
                      if (params) {
                        _params.push(params);
                      }
                      if (needQuery && data && dataType === "string") {
                        _params.push(data);
                      }
                      if (_params.length > 0) {
                        url += (url.indexOf("?") === -1 ? "?" : "&") + _params.join("&");
                      }
                      engine.open(options2.method, url);
                      try {
                        engine.withCredentials = !!options2.withCredentials;
                        engine.timeout = options2.timeout || 0;
                        if (responseType !== "stream") {
                          engine.responseType = responseType;
                        }
                      } catch (e) {
                      }
                      var customContentType = options2.headers[contentType] || options2.headers[contentTypeLowerCase];
                      var _contentType = "application/x-www-form-urlencoded";
                      if (utils.trim((customContentType || "").toLowerCase()) === _contentType) {
                        data = utils.formatParams(data);
                      } else if (!utils.isFormData(data) && ["object", "array"].indexOf(utils.type(data)) !== -1) {
                        _contentType = "application/json;charset=utf-8";
                        data = JSON.stringify(data);
                      }
                      if (!(customContentType || needQuery)) {
                        options2.headers[contentType] = _contentType;
                      }
                      for (var k in options2.headers) {
                        if (k === contentType && utils.isFormData(data)) {
                          delete options2.headers[k];
                        } else {
                          try {
                            engine.setRequestHeader(k, options2.headers[k]);
                          } catch (e) {
                          }
                        }
                      }
                      function onresult(handler, data2, type) {
                        enqueueIfLocked(responseInterceptor.p, function() {
                          if (handler) {
                            if (type) {
                              data2.request = options2;
                            }
                            var ret = handler.call(responseInterceptor, data2, Promise);
                            data2 = ret === void 0 ? data2 : ret;
                          }
                          if (!isPromise(data2)) {
                            data2 = Promise[type === 0 ? "resolve" : "reject"](data2);
                          }
                          data2.then(function(d) {
                            resolve(d);
                          }).catch(function(e) {
                            reject(e);
                          });
                        });
                      }
                      function onerror(e) {
                        e.engine = engine;
                        onresult(responseInterceptor.onerror, e, -1);
                      }
                      function Err(msg, status) {
                        this.message = msg;
                        this.status = status;
                      }
                      engine.onload = function() {
                        try {
                          var response = engine.response || engine.responseText;
                          if (response && options2.parseJson && (engine.getResponseHeader(contentType) || "").indexOf("json") !== -1 && !utils.isObject(response)) {
                            response = JSON.parse(response);
                          }
                          var headers = engine.responseHeaders;
                          if (!headers) {
                            headers = {};
                            var items = (engine.getAllResponseHeaders() || "").split("\r\n");
                            items.pop();
                            items.forEach(function(e2) {
                              if (!e2)
                                return;
                              var key = e2.split(":")[0];
                              headers[key] = engine.getResponseHeader(key);
                            });
                          }
                          var status = engine.status;
                          var statusText = engine.statusText;
                          var _data = { data: response, headers, status, statusText };
                          utils.merge(_data, engine._response);
                          if (status >= 200 && status < 300 || status === 304) {
                            _data.engine = engine;
                            _data.request = options2;
                            onresult(responseInterceptor.handler, _data, 0);
                          } else {
                            var e = new Err(statusText, status);
                            e.response = _data;
                            onerror(e);
                          }
                        } catch (e2) {
                          onerror(new Err(e2.msg, engine.status));
                        }
                      };
                      engine.onerror = function(e) {
                        onerror(new Err(e.msg || "Network Error", 0));
                      };
                      engine.ontimeout = function() {
                        onerror(new Err("timeout [ " + engine.timeout + "ms ]", 1));
                      };
                      engine._options = options2;
                      setTimeout(function() {
                        engine.send(needQuery ? null : data);
                      }, 0);
                    }
                    enqueueIfLocked(requestInterceptor.p, function() {
                      utils.merge(options, JSON.parse(JSON.stringify(_this.config)));
                      var headers = options.headers;
                      headers[contentType] = headers[contentType] || headers[contentTypeLowerCase] || "";
                      delete headers[contentTypeLowerCase];
                      options.body = data || options.body;
                      url = utils.trim(url || "");
                      options.method = options.method.toUpperCase();
                      options.url = url;
                      var ret = options;
                      if (requestInterceptorHandler) {
                        ret = requestInterceptorHandler.call(requestInterceptor, options, Promise) || options;
                      }
                      if (!isPromise(ret)) {
                        ret = Promise.resolve(ret);
                      }
                      ret.then(function(d) {
                        if (d === options) {
                          makeRequest(d);
                        } else {
                          resolve(d);
                        }
                      }, function(err) {
                        reject(err);
                      });
                    });
                  });
                  promise.engine = engine;
                  return promise;
                }
              }, {
                key: "all",
                value: function all(promises) {
                  return Promise.all(promises);
                }
              }, {
                key: "spread",
                value: function spread(callback) {
                  return function(arr) {
                    return callback.apply(null, arr);
                  };
                }
              }]);
              return Fly2;
            }();
            Fly.default = Fly;
            ["get", "post", "put", "patch", "head", "delete"].forEach(function(e) {
              Fly.prototype[e] = function(url, data, option) {
                return this.request(url, data, utils.merge({ method: e }, option));
              };
            });
            ["lock", "unlock", "clear"].forEach(function(e) {
              Fly.prototype[e] = function() {
                this.interceptors.request[e]();
              };
            });
            ;
            module2.exports = Fly;
          }
          /******/
        ])
      );
    });
  }
});
export default require_fly();
//# sourceMappingURL=flyio_dist_npm_fly.js.map
