(()=>{var n={3949:(n,_,t)=>{let e,r={};r.__wbindgen_placeholder__=n.exports;const{TextDecoder:o,TextEncoder:i}=t(3837);let a=new o("utf-8",{ignoreBOM:!0,fatal:!0});a.decode();let d=new Uint8Array;function s(){return 0===d.byteLength&&(d=new Uint8Array(e.memory.buffer)),d}function c(n,_){return a.decode(s().subarray(n,n+_))}const l=new Array(32).fill(void 0);l.push(void 0,null,!0,!1);let g=l.length;function w(n){g===l.length&&l.push(l.length+1);const _=g;return g=l[_],l[_]=n,_}let b=0,p=new i("utf-8");const u="function"==typeof p.encodeInto?function(n,_){return p.encodeInto(n,_)}:function(n,_){const t=p.encode(n);return _.set(t),{read:n.length,written:t.length}};function f(n,_,t){if(void 0===t){const t=p.encode(n),e=_(t.length);return s().subarray(e,e+t.length).set(t),b=t.length,e}let e=n.length,r=_(e);const o=s();let i=0;for(;i<e;i++){const _=n.charCodeAt(i);if(_>127)break;o[r+i]=_}if(i!==e){0!==i&&(n=n.slice(i)),r=t(r,e,e=i+3*n.length);const _=s().subarray(r+i,r+e);i+=u(n,_).written}return b=i,r}let y=new Int32Array;function m(){return 0===y.byteLength&&(y=new Int32Array(e.memory.buffer)),y}function h(n){const _=function(n){return l[n]}(n);return function(n){n<36||(l[n]=g,g=n)}(n),_}n.exports.format=function(n,_){try{const o=e.__wbindgen_add_to_stack_pointer(-16),i=f(n,e.__wbindgen_malloc,e.__wbindgen_realloc),a=b,d=f(_,e.__wbindgen_malloc,e.__wbindgen_realloc),s=b;e.format(o,i,a,d,s);var t=m()[o/4+0],r=m()[o/4+1];return c(t,r)}finally{e.__wbindgen_add_to_stack_pointer(16),e.__wbindgen_free(t,r)}},n.exports.get_config=function(n){try{const d=e.__wbindgen_add_to_stack_pointer(-16),s=f(n,e.__wbindgen_malloc,e.__wbindgen_realloc),l=b;e.get_config(d,s,l);var _=m()[d/4+0],t=m()[d/4+1],r=m()[d/4+2],o=m()[d/4+3],i=_,a=t;if(o)throw i=0,a=0,h(r);return c(i,a)}finally{e.__wbindgen_add_to_stack_pointer(16),e.__wbindgen_free(i,a)}},n.exports.lint=function(n){try{const r=e.__wbindgen_add_to_stack_pointer(-16),o=f(n,e.__wbindgen_malloc,e.__wbindgen_realloc),i=b;e.lint(r,o,i);var _=m()[r/4+0],t=m()[r/4+1];return c(_,t)}finally{e.__wbindgen_add_to_stack_pointer(16),e.__wbindgen_free(_,t)}},n.exports.native_types=function(n){try{const r=e.__wbindgen_add_to_stack_pointer(-16),o=f(n,e.__wbindgen_malloc,e.__wbindgen_realloc),i=b;e.native_types(r,o,i);var _=m()[r/4+0],t=m()[r/4+1];return c(_,t)}finally{e.__wbindgen_add_to_stack_pointer(16),e.__wbindgen_free(_,t)}},n.exports.referential_actions=function(n){try{const r=e.__wbindgen_add_to_stack_pointer(-16),o=f(n,e.__wbindgen_malloc,e.__wbindgen_realloc),i=b;e.referential_actions(r,o,i);var _=m()[r/4+0],t=m()[r/4+1];return c(_,t)}finally{e.__wbindgen_add_to_stack_pointer(16),e.__wbindgen_free(_,t)}},n.exports.preview_features=function(){try{const t=e.__wbindgen_add_to_stack_pointer(-16);e.preview_features(t);var n=m()[t/4+0],_=m()[t/4+1];return c(n,_)}finally{e.__wbindgen_add_to_stack_pointer(16),e.__wbindgen_free(n,_)}},n.exports.text_document_completion=function(n,_){try{const o=e.__wbindgen_add_to_stack_pointer(-16),i=f(n,e.__wbindgen_malloc,e.__wbindgen_realloc),a=b,d=f(_,e.__wbindgen_malloc,e.__wbindgen_realloc),s=b;e.text_document_completion(o,i,a,d,s);var t=m()[o/4+0],r=m()[o/4+1];return c(t,r)}finally{e.__wbindgen_add_to_stack_pointer(16),e.__wbindgen_free(t,r)}},n.exports.code_actions=function(n,_){try{const o=e.__wbindgen_add_to_stack_pointer(-16),i=f(n,e.__wbindgen_malloc,e.__wbindgen_realloc),a=b,d=f(_,e.__wbindgen_malloc,e.__wbindgen_realloc),s=b;e.code_actions(o,i,a,d,s);var t=m()[o/4+0],r=m()[o/4+1];return c(t,r)}finally{e.__wbindgen_add_to_stack_pointer(16),e.__wbindgen_free(t,r)}},n.exports.debug_panic=function(){e.debug_panic()};class k{static __wrap(n){const _=Object.create(k.prototype);return _.ptr=n,_}__destroy_into_raw(){const n=this.ptr;return this.ptr=0,n}free(){const n=this.__destroy_into_raw();e.__wbg_adonetstring_free(n)}constructor(n){try{const r=e.__wbindgen_add_to_stack_pointer(-16),o=f(n,e.__wbindgen_malloc,e.__wbindgen_realloc),i=b;e.adonetstring_new(r,o,i);var _=m()[r/4+0],t=m()[r/4+1];if(m()[r/4+2])throw h(t);return k.__wrap(_)}finally{e.__wbindgen_add_to_stack_pointer(16)}}get(n){try{const r=e.__wbindgen_add_to_stack_pointer(-16),o=f(n,e.__wbindgen_malloc,e.__wbindgen_realloc),i=b;e.adonetstring_get(r,this.ptr,o,i);var _=m()[r/4+0],t=m()[r/4+1];let a;return 0!==_&&(a=c(_,t).slice(),e.__wbindgen_free(_,1*t)),a}finally{e.__wbindgen_add_to_stack_pointer(16)}}set(n,_){try{const o=e.__wbindgen_add_to_stack_pointer(-16),i=f(n,e.__wbindgen_malloc,e.__wbindgen_realloc),a=b,d=f(_,e.__wbindgen_malloc,e.__wbindgen_realloc),s=b;e.adonetstring_set(o,this.ptr,i,a,d,s);var t=m()[o/4+0],r=m()[o/4+1];let l;return 0!==t&&(l=c(t,r).slice(),e.__wbindgen_free(t,1*r)),l}finally{e.__wbindgen_add_to_stack_pointer(16)}}to_string(){try{const t=e.__wbindgen_add_to_stack_pointer(-16);e.adonetstring_to_string(t,this.ptr);var n=m()[t/4+0],_=m()[t/4+1];return c(n,_)}finally{e.__wbindgen_add_to_stack_pointer(16),e.__wbindgen_free(n,_)}}}n.exports.AdoNetString=k;class v{static __wrap(n){const _=Object.create(v.prototype);return _.ptr=n,_}__destroy_into_raw(){const n=this.ptr;return this.ptr=0,n}free(){const n=this.__destroy_into_raw();e.__wbg_jdbcstring_free(n)}constructor(n){try{const r=e.__wbindgen_add_to_stack_pointer(-16),o=f(n,e.__wbindgen_malloc,e.__wbindgen_realloc),i=b;e.jdbcstring_new(r,o,i);var _=m()[r/4+0],t=m()[r/4+1];if(m()[r/4+2])throw h(t);return v.__wrap(_)}finally{e.__wbindgen_add_to_stack_pointer(16)}}sub_protocol(){try{const t=e.__wbindgen_add_to_stack_pointer(-16);e.jdbcstring_sub_protocol(t,this.ptr);var n=m()[t/4+0],_=m()[t/4+1];return c(n,_)}finally{e.__wbindgen_add_to_stack_pointer(16),e.__wbindgen_free(n,_)}}server_name(){try{const t=e.__wbindgen_add_to_stack_pointer(-16);e.jdbcstring_server_name(t,this.ptr);var n=m()[t/4+0],_=m()[t/4+1];let r;return 0!==n&&(r=c(n,_).slice(),e.__wbindgen_free(n,1*_)),r}finally{e.__wbindgen_add_to_stack_pointer(16)}}instance_name(){try{const t=e.__wbindgen_add_to_stack_pointer(-16);e.jdbcstring_instance_name(t,this.ptr);var n=m()[t/4+0],_=m()[t/4+1];let r;return 0!==n&&(r=c(n,_).slice(),e.__wbindgen_free(n,1*_)),r}finally{e.__wbindgen_add_to_stack_pointer(16)}}port(){const n=e.jdbcstring_port(this.ptr);return 16777215===n?void 0:n}get(n){try{const r=e.__wbindgen_add_to_stack_pointer(-16),o=f(n,e.__wbindgen_malloc,e.__wbindgen_realloc),i=b;e.adonetstring_get(r,this.ptr,o,i);var _=m()[r/4+0],t=m()[r/4+1];let a;return 0!==_&&(a=c(_,t).slice(),e.__wbindgen_free(_,1*t)),a}finally{e.__wbindgen_add_to_stack_pointer(16)}}set(n,_){try{const o=e.__wbindgen_add_to_stack_pointer(-16),i=f(n,e.__wbindgen_malloc,e.__wbindgen_realloc),a=b,d=f(_,e.__wbindgen_malloc,e.__wbindgen_realloc),s=b;e.adonetstring_set(o,this.ptr,i,a,d,s);var t=m()[o/4+0],r=m()[o/4+1];let l;return 0!==t&&(l=c(t,r).slice(),e.__wbindgen_free(t,1*r)),l}finally{e.__wbindgen_add_to_stack_pointer(16)}}to_string(){try{const t=e.__wbindgen_add_to_stack_pointer(-16);e.jdbcstring_to_string(t,this.ptr);var n=m()[t/4+0],_=m()[t/4+1];return c(n,_)}finally{e.__wbindgen_add_to_stack_pointer(16),e.__wbindgen_free(n,_)}}}n.exports.JdbcString=v,n.exports.__wbindgen_error_new=function(n,_){return w(new Error(c(n,_)))},n.exports.__wbindgen_string_new=function(n,_){return w(c(n,_))},n.exports.__wbindgen_throw=function(n,_){throw new Error(c(n,_))};const x=t(1017).join(__dirname,"prisma_fmt_build_bg.wasm"),j=t(7147).readFileSync(x),A=new WebAssembly.Module(j),O=new WebAssembly.Instance(A,r);e=O.exports,n.exports.__wasm=e},7147:n=>{"use strict";n.exports=require("fs")},1017:n=>{"use strict";n.exports=require("path")},3837:n=>{"use strict";n.exports=require("util")}},_={};function t(e){var r=_[e];if(void 0!==r)return r.exports;var o=_[e]={exports:{}};return n[e](o,o.exports,t),o.exports}t.n=n=>{var _=n&&n.__esModule?()=>n.default:()=>n;return t.d(_,{a:_}),_},t.d=(n,_)=>{for(var e in _)t.o(_,e)&&!t.o(n,e)&&Object.defineProperty(n,e,{enumerable:!0,get:_[e]})},t.o=(n,_)=>Object.prototype.hasOwnProperty.call(n,_),(()=>{"use strict";var n=t(3949),_=t.n(n);const e=process.argv[2];let r="";const o=process.stdin;o.setEncoding("utf8"),o.on("readable",(()=>{let n;for(;null!==(n=o.read());)r+=n})),o.on("end",(()=>{!function(){try{const n=_().format(r,e);console.log(n),process.exit(0)}catch(n){console.error(n),process.exit(1)}}()})),setTimeout((()=>{process.exit(1)}),1e4)})()})();