function mobi(){var T='',Bb='" for "gwt:onLoadErrorFn"',zb='" for "gwt:onPropertyErrorFn"',mb='"><\/script>',bb='#',Mb='&',rc='.cache.html',db='/',pb='//',$b='046DD6FEB6686EDCDCBBF22BD88CE8E6',_b='0B91742A3E370CD516B2498C982F8804',ac='11747E90AF22D94F98739E048124020A',bc='2E134758E3ED4ED94ACCD1C370C2A5A4',cc='571D8D1805E83A0374DAD12665FCAF48',dc='5842B17A33D7726A835CF174214DEAAB',ec='64489EFA8A732DBC8F64D45D7A41838E',fc='6C5884DDF47FA21A3808ABFE3F4B110E',gc='8595E143979802B7C4E0FEA787D1CD61',hc='90C0D8902456331C61F5BD09B64B05CE',ic='91188AFFF3E6151B60B5E3B233732BA1',qc=':',tb='::',tc='<script defer="defer">mobi.onInjectionDone(\'mobi\')<\/script>',lb='<script id="',wb='=',cb='?',jc='AB076C982C726BEB5B74EF962F415FEB',kc='AB9D5293803A6E90151F7A4997D2114B',yb='Bad handler "',lc='CF846DBEF3ED93AA3E9C808CF9596C4C',sc='DOMContentLoaded',mc='E6C190E8A30D792B1FA670A3F4EDEC96',nc='E7389864FD5E620D59178E3A599E58A0',oc='FAB2DAEAF7D56840D6ECB330868C5C7D',pc='FBF21F3752C2192A1856AF000835773A',nb='SCRIPT',kb='__gwt_marker_mobi',Sb='android',ob='base',gb='baseUrl',X='begin',W='bootstrap',fb='clear.cache.gif',vb='content',Ub='desktop',ab='end',Lb='formfactor',Y='gwt.codesvr=',Z='gwt.hosted=',$='gwt.hybrid',Ab='gwt:onLoadErrorFn',xb='gwt:onPropertyErrorFn',ub='gwt:property',Jb='high',Yb='hosted.html?mobi',Cb='iframe',eb='img',Wb='ios',Qb='ipad',Nb='iphone',Ob='ipod',Db="javascript:''",Xb='loadExternalRefs',qb='meta',Gb='mgwt.density',Kb='mgwt.formfactor',Vb='mgwt.os',Hb='mid',U='mobi',ib='mobi.nocache.js',sb='mobi::',Tb='mobile',Fb='moduleRequested',_='moduleStartup',rb='name',Pb='phone',Eb='position:absolute;width:0;height:0;border:none',hb='script',Zb='selectingPermutation',V='startup',Rb='tablet',jb='undefined',Ib='xhigh';var m=window,n=document,o=m.__gwtStatsEvent?function(a){return m.__gwtStatsEvent(a)}:null,p=m.__gwtStatsSessionId?m.__gwtStatsSessionId:null,q,r,s,t=T,u={},v=[],w=[],A=[],B=0,C,D;o&&o({moduleName:U,sessionId:p,subSystem:V,evtGroup:W,millis:(new Date).getTime(),type:X});if(!m.__gwt_stylesLoaded){m.__gwt_stylesLoaded={}}if(!m.__gwt_scriptsLoaded){m.__gwt_scriptsLoaded={}}function F(){var b=false;try{var c=m.location.search;return (c.indexOf(Y)!=-1||(c.indexOf(Z)!=-1||m.external&&m.external.gwtOnLoad))&&c.indexOf($)==-1}catch(a){}F=function(){return b};return b}
function G(){if(q&&r){var b=n.getElementById(U);var c=b.contentWindow;if(F()){c.__gwt_getProperty=function(a){return L(a)}}mobi=null;c.gwtOnLoad(C,U,t,B);o&&o({moduleName:U,sessionId:p,subSystem:V,evtGroup:_,millis:(new Date).getTime(),type:ab})}}
function H(){function e(a){var b=a.lastIndexOf(bb);if(b==-1){b=a.length}var c=a.indexOf(cb);if(c==-1){c=a.length}var d=a.lastIndexOf(db,Math.min(c,b));return d>=0?a.substring(0,d+1):T}
function f(a){if(a.match(/^\w+:\/\//)){}else{var b=n.createElement(eb);b.src=a+fb;a=e(b.src)}return a}
function g(){var a=J(gb);if(a!=null){return a}return T}
function h(){var a=n.getElementsByTagName(hb);for(var b=0;b<a.length;++b){if(a[b].src.indexOf(ib)!=-1){return e(a[b].src)}}return T}
function i(){var a;if(typeof isBodyLoaded==jb||!isBodyLoaded()){var b=kb;var c;n.write(lb+b+mb);c=n.getElementById(b);a=c&&c.previousSibling;while(a&&a.tagName!=nb){a=a.previousSibling}if(c){c.parentNode.removeChild(c)}if(a&&a.src){return e(a.src)}}return T}
function j(){var a=n.getElementsByTagName(ob);if(a.length>0){return a[a.length-1].href}return T}
function k(){var a=n.location;return a.href==a.protocol+pb+a.host+a.pathname+a.search+a.hash}
var l=g();if(l==T){l=h()}if(l==T){l=i()}if(l==T){l=j()}if(l==T&&k()){l=e(n.location.href)}l=f(l);t=l;return l}
function I(){var b=document.getElementsByTagName(qb);for(var c=0,d=b.length;c<d;++c){var e=b[c],f=e.getAttribute(rb),g;if(f){f=f.replace(sb,T);if(f.indexOf(tb)>=0){continue}if(f==ub){g=e.getAttribute(vb);if(g){var h,i=g.indexOf(wb);if(i>=0){f=g.substring(0,i);h=g.substring(i+1)}else{f=g;h=T}u[f]=h}}else if(f==xb){g=e.getAttribute(vb);if(g){try{D=eval(g)}catch(a){alert(yb+g+zb)}}}else if(f==Ab){g=e.getAttribute(vb);if(g){try{C=eval(g)}catch(a){alert(yb+g+Bb)}}}}}}
function J(a){var b=u[a];return b==null?null:b}
function K(a,b){var c=A;for(var d=0,e=a.length-1;d<e;++d){c=c[a[d]]||(c[a[d]]=[])}c[a[e]]=b}
function L(a){var b=w[a](),c=v[a];if(b in c){return b}var d=[];for(var e in c){d[c[e]]=e}if(D){D(a,d,b)}throw null}
var M;function N(){if(!M){M=true;var a=n.createElement(Cb);a.src=Db;a.id=U;a.style.cssText=Eb;a.tabIndex=-1;n.body.appendChild(a);o&&o({moduleName:U,sessionId:p,subSystem:V,evtGroup:_,millis:(new Date).getTime(),type:Fb});a.contentWindow.location.replace(t+P)}}
w[Gb]=function(){if(!window.devicePixelRatio){return Hb}if(window.devicePixelRatio>1.5){return Ib}else if(window.devicePixelRatio>1){return Jb}return Hb};v[Gb]={high:0,mid:1,xhigh:2};w[Kb]=function(){var a=location.search;var b=a.indexOf(Lb);if(b>=0){var c=a.substring(b);var d=c.indexOf(wb)+1;var e=c.indexOf(Mb);if(e==-1){e=c.length}return c.substring(d,e)}var f=navigator.userAgent.toLowerCase();if(f.indexOf(Nb)!=-1||f.indexOf(Ob)!=-1){return Pb}else if(f.indexOf(Qb)!=-1){return Rb}else if(f.indexOf(Sb)!=-1){if(f.indexOf(Tb)!=-1){return Pb}else{return Rb}}return Ub};v[Kb]={desktop:0,phone:1,tablet:2};w[Vb]=function(){var a=navigator.userAgent.toLowerCase();if(a.indexOf(Nb)!=-1||a.indexOf(Ob)!=-1){return Wb}else if(a.indexOf(Qb)!=-1){return Wb}else if(a.indexOf(Sb)!=-1){return Sb}return Wb};v[Vb]={android:0,ios:1};mobi.onScriptLoad=function(){if(M){r=true;G()}};mobi.onInjectionDone=function(){q=true;o&&o({moduleName:U,sessionId:p,subSystem:V,evtGroup:Xb,millis:(new Date).getTime(),type:ab});G()};I();H();var O;var P;if(F()){if(m.external&&(m.external.initModule&&m.external.initModule(U))){m.location.reload();return}P=Yb;O=T}o&&o({moduleName:U,sessionId:p,subSystem:V,evtGroup:W,millis:(new Date).getTime(),type:Zb});if(!F()){try{K([Jb,Ub,Wb],$b);K([Hb,Pb,Wb],_b);K([Ib,Pb,Wb],ac);K([Ib,Ub,Wb],bc);K([Jb,Rb,Wb],cc);K([Jb,Ub,Sb],dc);K([Hb,Ub,Wb],ec);K([Jb,Pb,Sb],fc);K([Jb,Pb,Wb],gc);K([Ib,Ub,Sb],hc);K([Hb,Rb,Sb],ic);K([Jb,Rb,Sb],jc);K([Hb,Rb,Wb],kc);K([Ib,Rb,Sb],lc);K([Hb,Pb,Sb],mc);K([Hb,Ub,Sb],nc);K([Ib,Rb,Wb],oc);K([Ib,Pb,Sb],pc);O=A[L(Gb)][L(Kb)][L(Vb)];var Q=O.indexOf(qc);if(Q!=-1){B=Number(O.substring(Q+1));O=O.substring(0,Q)}P=O+rc}catch(a){return}}var R;function S(){if(!s){s=true;G();if(n.removeEventListener){n.removeEventListener(sc,S,false)}if(R){clearInterval(R)}}}
if(n.addEventListener){n.addEventListener(sc,function(){N();S()},false)}var R=setInterval(function(){if(/loaded|complete/.test(n.readyState)){N();S()}},50);o&&o({moduleName:U,sessionId:p,subSystem:V,evtGroup:W,millis:(new Date).getTime(),type:ab});o&&o({moduleName:U,sessionId:p,subSystem:V,evtGroup:Xb,millis:(new Date).getTime(),type:X});n.write(tc)}
mobi();