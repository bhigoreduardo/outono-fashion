import puppeteer from 'puppeteer';
import fs from 'fs';

var produtos = [];

(async () => {
    fs.readFile('links.json', 'utf8', function (err, data) {
        if (err) throw new Error('Somethings went wrong.');

        var jsonData = JSON.parse(data);
        var jsonIndex = Object.keys(jsonData);

        getData(jsonData[6].href);
    });

})();

// for (let i = 0; i < jsonIndex.length; i++) {
        //     (function (i) {
        //         setTimeout(function () {
        //             getData(jsonData[i].href);
        //         }, 5000 * i);
        //     })(i);
        // };

async function getData(href) {
    const browser = await puppeteer.launch();
    const page = await browser.newPage();

    await page.goto(href);

    const produtoInfo = await page.evaluate(() => {
        const nodeImgList = document.querySelectorAll('#gallery .gallery-thumbs a');
        const linkImgArray = [...nodeImgList];
        const linkImgList = linkImgArray.map(({ href }) => ({ href }));

        const name = document.querySelector('.product-name').innerHTML;
        const description = document.querySelector('.tabs-products .box-description p').innerHTML;
        const price = document.querySelector('#stock-available .catalog-detail-price-line .catalog-detail-price-value').innerHTML;

        const scriptProduto = `INSERT INTO produto (nome, descricao, detalhe, largura, altura, comprimento, peso, data_cadastro, data_atualizacao, ativo, genero_id, categoria_id, tipo_id, marca_id) VALUES ('${name}', '${name}', '${description}<br>Produto enviado com nota fiscal!', 27.00, 14.00, 34.00, 500.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, 4, 3, 18, 7);`;
        const imgName = name.toLocaleLowerCase().replaceAll(' ', '-').normalize('NFD').replace(/[\u0300-\u036f]/g, "");

        return {
            nome: name,
            descricao: name,
            detalhe: description,
            preco: price,
            scriptProduto: scriptProduto,
            imgNome: imgName,
            linksImg: linkImgList
        }
    });

    fs.writeFile('produto.json', JSON.stringify(produtoInfo, null, 2), err => {
        if (err) throw new Error('Somethings went wrong.');
        console.log('Well done!');
    });

    await browser.close();
}


// (async () => {
//     const browser = await puppeteer.launch();
//     const page = await browser.newPage();

//     await page.goto('https://www.dafiti.com.br/bolsas-e-acessorios-infantis/oculos/');

//     const linkList = await page.evaluate(() => {
//         const nodeList = document.querySelectorAll('.product-box .product-box-image a');

//         const linkArray = [...nodeList];

//         const linkList = linkArray.map(({ href }) => ({ href }));

//         return linkList;
//     });

//     fs.writeFile('links.json', JSON.stringify(linkList, null, 2), err => {
//         if (err) throw new Error('Somethings went wrong.');
//         console.log('Well done!');
//     });

//     await browser.close();
// })();