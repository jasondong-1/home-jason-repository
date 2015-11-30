function initEnv() {
    var layer = $("#background,#progressBar");
    $(document).ajaxStart(function () {
        layer.show()
    }).ajaxComplete(function () {
        layer.hide();
    });
}
$(document).ready(function () {
    var html = "<div id='background'></div><div id='progressBar'></div>";
    $("#layer").html(html);
    initEnv();
});
