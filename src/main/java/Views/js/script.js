const sideMenu = document.querySelector("aside");
const menuBtn = document.querySelector("#menu-btn");
const closeBtn = document.querySelector("#close-btn");
const themeToggler = document.querySelector(".theme-toggler");
const tbodyAllclients = document.querySelector("#tbody-clients-all");
const clientsAllbtn = document.querySelector("#btn-all-clients");
const createBtn = document.querySelector("#btn-create-client");
const containerCreateDeleteUpdateclientsCrud= document.querySelector(".container-create-delete-update-clients-crud");
const titleCreateDeleteUpdate = document.querySelector(
  ".title-create-delete-update-client"
);
const tbodyclientsRecentToday = document.querySelector(
  "#tbody-clients-today"
);
const url = "http://localhost:8080/client";
let editBtn;
let deleteBtn;


const getAllclients = async () => {
  try {
    const response = await fetch(url + "/clients", {
      method: "GET",
    });
    if (!response.ok) {
      throw new Error(`Erro HTTP! Status: ${response.status}`);
    }
    const clients = await response.json();
    return clients;
  } catch (error) {
    console.error("Erro ao buscar Clientes:", error);
    return null;
  }
};

async function getClient(id) {
  try {
    const response = await fetch(`${url}/${id}`, {
      method: 'GET'
    });
    if (!response.ok) {
      throw new Error('Erro ao buscar Clientes');
    }
    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Erro:', error);
    throw error;
  }
};

const allclients = async () => {
  const allclients = await getAllclients();

  tbodyAllclients.innerHTML = "";
  if (allclients!= null) {
    for (i = 0; i < allclients.length; i++) {
      const client = allclients[i];
      const tr = document.createElement("tr");
      const trContent = `
     <td>${client.codeClient}</td>
     <td>${client.name}</td>
     <td>${client.cpf}</td>
     <td>${client.age}</td>
     <td><span id="${
      client.id
        }" class="material-symbols-outlined btn-edit-client">
        edit</span></td>
        <td><span id="${
          client.id
        }" class="material-symbols-outlined btn-delete-client">
        delete</span></td>
        `;
      tr.innerHTML = trContent;
      tbodyAllclients.appendChild(tr);
    }
    editBtn = document.querySelector(".btn-edit-client");
    deleteBtn = document.querySelector(".btn-delete-client");
    editBtn.addEventListener("click", edit);
    deleteBtn.addEventListener("click", deleteClient);
  }
};

clientsAllbtn.addEventListener("click", allclients);

const searchNumber = async () => {
  const input = document.getElementById("input-filter-client");
  const id = input.value;
  try {
    const client = await getClient(id);
    if (client != null) {
      tbodyAllclients.innerHTML = "";
      const tr = document.createElement("tr");
      const trContent = `
        <td>${client.codeClient}</td>
        <td>${client.name}</td>
        <td>${client.cpf}</td>
        <td>${client.age}</td>
        <td><span id="${
          client.id
        }" class="material-symbols-outlined btn-edit-client">edit</span></td>
        <td><span id="${
          client.id
        }" class="material-symbols-outlined btn-delete-client">delete</span></td>
      `;
      tr.innerHTML = trContent;
      tbodyAllclients.appendChild(tr);
    }
    editBtn = document.querySelector(".btn-edit-client");
    deleteBtn = document.querySelector(".btn-delete-client");
    editBtn.addEventListener("click", editClient);
    
  } catch (error) {
    console.error("Erro ao buscar produto:", error);
  }
};

const createClient = function () {
  console.log("Olá")

  containerCreateDeleteUpdateclientsCrud.innerHTML = "";
  
  titleCreateDeleteUpdate.innerHTML = "Criar  Produto";

      const containerCreateForm = `<div class="create-update-delete-form-client">
      <form action="">
          <div class="form-group-text">
              <label for="nome">Nome</label>
              <input type="text" id="nome" placeholder="Nome produto">
              <label for="cpf">CPF</label>
              <input type="text" id="cpf" placeholder="Descreva o produto">
          </div>
          <div class="form-group-number">
              <div class="form-content-number">
                  <label for="age">Idade</label>
                  <input type="number" id="age" min="0" step="1" required>
              </div>
          </div>
          <button  id="btn-create-clientconfirm" class="btn-add-confirm-or-delete" type="button">Confirmar</button>
      </form>
  </div>
  `;
        containerCreateDeleteUpdateclientsCrud.innerHTML = containerCreateForm;
        const btnCreateConfirm = document.querySelector("#btn-create-clientconfirm");
        btnCreateConfirm.addEventListener("click", confirmFormCreater);
        
};

createBtn.addEventListener("click", createClient);

const confirmFormCreater = async (event) => {
  event.preventDefault(); 
  
  const name = document.getElementById('nome').value;
  const cpf = document.getElementById('cpf').value;
  const age = parseFloat(document.getElementById('age').value);

  const data = {
    name,
    cpf,
    age
  };
  try {
    const response = await fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    });
    if (!response.ok) {
      throw new Error('Erro ao enviar dados para a API');
    }
    const responseData = await response.json();
    console.log('Resposta da API:', responseData);
  } catch (error) {
    console.error('Erro ao enviar dados para a API:', error);
  }
};
const editClient = async function () {
  const btnEditIdAll = document.querySelectorAll(".btn-edit-client");
  btnEditIdAll.forEach(btnEditI => {
    btnEditI.addEventListener("click", async function(event) {
      const id = event.target.closest('.btn-edit-client').id;
      const client = await getClient(id);
      containerCreateDeleteUpdateclientsCrud.innerHTML = "";
      titleCreateDeleteUpdate.innerHTML = "Editar  Produto";
    
          const containerEdit = `<div class="create-update-delete-form-client">
          <div class="container-edit">
              <div class="edit-group-text">
                  <h3>Nome</h3>
                  <input type="text" id="name-edit" class="${client.codeClient}" value="${client.name}">
                  <h3>Descrição</h3>
                  <input type="text" id="cpf-edit" value="${client.cpf}">
              </div>
              <div class="edit-group-number">
                  <div class="edit-content-number">
                      <h3>Valor</h3>
                      <input type="number" id="age-edit" value="${client.age}">
                  </div>
              </div>
              <button id="btn-edit-clientconfirm" class="btn-add-confirm-or-delete" type="button">Confirmar</button>
          </div>
      </div>
      `;
            containerCreateDeleteUpdateclientsCrud.innerHTML = containerEdit;
            const btnEditConfirm = document.querySelector("#btn-edit-clientconfirm");
            btnEditConfirm.addEventListener("click", confirmFormEdit);
    });
  });
      
};

const confirmFormEdit = async (event) => {

  const ValidationId=document.getElementById('name-edit');
  const id=ValidationId.id;
  event.preventDefault(); 
  const name = document.getElementById('name-edit').value;
  const cpf = document.getElementById('cpf-edit').value;
  const age = parseInt(document.getElementById('age-edit').value);

  const data = {
    name,
    cpf,
    age,
  };
  try {
    const response = await fetch(url+"/"+id, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    });
    if (!response.ok) {
      throw new Error('Erro ao enviar dados para a API');
    }
    const responseData = await response.json();
    console.log('Resposta da API:', responseData);
    containerCreateDeleteUpdateclientsCrud.innerHTML = "";
  } catch (error) {
    console.error('Erro ao enviar dados para a API:', error);
  }
};
const deleteClient = async function () {
  const btnDeleteIdAll = document.querySelectorAll(".btn-delete-client");
  btnDeleteIdAll.forEach(btnDeleteId => {
    btnDeleteId.addEventListener("click", async function(event) {
      const id = event.target.closest('.btn-delete-client').id;
      const client = await getClient(id);
      containerCreateDeleteUpdateclientsCrud.innerHTML = "";
      titleCreateDeleteUpdate.innerHTML = "Deletar o Produto?";
    
          const containerDelete = `
          <div class="create-update-delete-form-client">
          <div class="container-delete">
              <div class="edit-group-text">
                  <h3>Id</h3>
                  <p class="id-delete" id="${client.codeClient}">${client.codeClient}</p>
                  <h3>Nome</h3>
                  <p class="name-delete">${client.name}</p>
                  <h3>Descrição</h3>
                  <p class="cpf-delete">${client.cpf}</p>
              </div>
              <div class="delete-group-number">
                  <div class="delete-content-number">
                      <h3>Valor</h3>
                      <p class="age-delete">${client.age}</p>
                  </div>
              </div>
              <div class="delete-content-select">
              <h3>Fornecedor</h3>
              <button id="btn-delete-clientconfirm" class="btn-add-confirm-or-delete"
                  type="button">Confirmar</button>
          </div>
      </div>
          `;
            containerCreateDeleteUpdateclientsCrud.innerHTML = containerDelete;
            const btnDeleteConfirm = document.querySelector("#btn-delete-clientconfirm");
            btnDeleteConfirm.addEventListener("click", confirmDelete);
    });
  });
      
};

const confirmDelete = async (event) => {
  event.preventDefault(); 
  const idDelete = document.querySelector('.name-delete');
  try {
    const response = await fetch(url+"/"+idDelete.id, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json'
      }
    });
    if (!response.ok) {
      throw new Error('Erro ao Deletar dados da API');
    }
    const responseData = await response.json();
    console.log('Resposta da API:', responseData);
    containerCreateDeleteUpdateclientsCrud.innerHTML = "";
  } catch (error) {
    console.error('Erro ao Deletar dados da API:', error);
  }
};
