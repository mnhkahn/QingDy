var cVSlide = {
    create: function(container, data) {
        this.container = container;
        this.liHeight = this.container.height() / 2;

        this.json = eval(data);
        this._showSlide();
        this.startMove();
/*        var THIS = this;
        $.ajax({
            type: "GET",
            dataType: "json",
            url: url,
            success: function(response) {
                THIS.json = eval(response);
                THIS._showSlide();
                THIS.startMove();
            },
            error: function(response) {
                console.error(response);
            }
        }); */
    },

};