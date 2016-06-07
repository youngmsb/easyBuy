$(function () {

    $(".menu-item p").on('click', function () {
        var $this = $(this);
        if (!$this.next("ul").is(":animated")) {
            $this.next("ul").slideToggle("normal", function () {
                if ($this.next("ul").is(":visible")) {
                    $this.find("i").removeClass("menu-down-arrow").addClass("menu-up-arrow");
                } else {
                    $this.find("i").removeClass("menu-up-arrow").addClass("menu-down-arrow");
                }
            });
        }
    });

    $(".menu li a").on('click', function () {
        $(".choosed").removeClass("choosed");
        $(this).addClass("choosed");
    });


});