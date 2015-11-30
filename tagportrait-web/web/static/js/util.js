function initEnv() {
    var ajaxbg = $("#background,#progressBar");
    ajaxbg.hide();
    var _timeout = null;
    $(document).ajaxStart(function () {
        _timeout = setTimeout(function () {
            ajaxbg.show()
        }, 300);
    }).ajaxStop(function () {
        if (_timeout) {
            clearTimeout(_timeout);
            _timeout = null;
        }
        ajaxbg.hide();
    });
}
$(document).ready(function () {
    initEnv();
});
