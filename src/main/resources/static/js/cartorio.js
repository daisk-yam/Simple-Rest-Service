// specify the columns
var columnDefs = [
    {headerName: "id", field: "id"},
    {headerName: "nome", field: "nome", editable: true},
    {
        headerName: "excluir", cellRenderer: function (a) {
            console.log(a);
            return '<button class="btn  btn-primary" onclick="excluir(' + a.data.id + ')">Excluir</button>';
        }
    },
];

function excluir(id) {
    console.log(id);
    var url = '/api/excluir';
    var success = function (response) {

        gridOptions.api.forEachNode(function (row, index) {
            if (row.data.id === id)
                gridOptions.api.updateRowData({remove: [row.data]});
        })
    }
    $.ajax({
        type: "POST",
        url: url,
        data: {id: id},
        success: success,
    });
}

function save(data) {
    console.log(data);
    var url = '/api/salvar';
    var success = function (a) {

    }
    $.ajax({
        type: "POST",
        dataType: 'json',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8',
        },
        url: url,
        data: JSON.stringify(data),
        success: success,
    });
}

// specify the data
var rowData = [];

// let the grid know which columns and what data to use
var gridOptions = {
    columnDefs: columnDefs,
    rowData: rowData,
    onCellEditingStopped: function (event) {
        save(event.data)
    }
};


var vm = new Vue({
    el: '#template',
    data: {
        cadastro: {
            nome: '',
        }
    },
    mounted: function () {
        // lookup the container we want the Grid to use
        var eGridDiv = document.querySelector('#myGrid');

        this.$http.get('/api/cartorios').then(function (response) {
            gridOptions.rowData = response.body;
            console.log(response.body);
            new agGrid.Grid(eGridDiv, gridOptions);
        })
        // create the grid passing in the div to use together with the columns & data we want to use

    },
    methods: {
        adicionar: function (value) {
            if (!value) return false;
            console.log(value);
            this.$http.post('/api/salvar', value)
                .then(function (response) {
                    console.log(response);
                    gridOptions.api.updateRowData({add: [{id: response.body, nome: value.nome}]})
                })
                .catch(function (reason) {
                })

            console.log(value);
        },
        enterClicked: function (a) {
            this.$refs.addCartorio.click();
        }
    }

})



