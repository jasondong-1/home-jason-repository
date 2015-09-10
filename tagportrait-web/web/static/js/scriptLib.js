/**
 * Created by dzj on 14-8-18.
 */
/**
 * 判断字符串是否为空
 * @param s
 * @returns {boolean}
 */
function isEmpty(s) {
    return ((s == null) || (s.length == 0) || (s == "") || (Trim(s)).length == 0);
}

/**
 * 去除字符串中的空白部分
 * @param s
 * @returns {*}
 */
function Trim(s) {
    if (s != null)
        return RTrim(LTrim(s));
}

function LTrim(s) {
    s = "" + s;
    for (var i = 0; i < s.length; i++)
        if (s.charAt(i) != ' ')
            return s.substring(i, s.length);
    return "";
}

function RTrim(s) {
    for (var i = s.length - 1; i >= 0; i--)
        if (s.charAt(i) != ' ')
            return s.substring(0, i + 1);
    return "";
}

/**
 * 实例化数组
 * @constructor
 */
function StringBuilder() {
    this.data = new Array();
}

/**
 * 拼接字符串,可以连续拼接
 * @return {}
 */
StringBuilder.prototype.append = function (str) {
    this.data.push(str);
    return this;
}

/**
 * 转成字符串输出
 * @return {}
 */
StringBuilder.prototype.toString = function () {
    if (arguments.length > 0) {
        return this.data.join(arguments[0]);
    }
    else {
        return this.data.join('');
    }
}


/**
 * 清空字符串数组
 */
StringBuilder.prototype.clear = function () {
    this.data = [];
    this.data.length = 0;
}

/**
 * 删除数组的最后一个字符
 * @returns {}
 */
StringBuilder.prototype.delete = function () {
    return this.data.pop();
}

Function.prototype.getName = function () {
    return this.name || this.toString().match(/function\s*([^(]*)\(/)[1]
}
/**
 * 判断字符是否含有中文
 * @param s
 * @returns {boolean}
 */
function hasChinese(s) {
    var patrn = /[\u4E00-\u9FA5]|[\uFE30-\uFFA0]/gi;
    if (!patrn.exec(s)) {
        return false;
    } else {
        return true;
    }
}
