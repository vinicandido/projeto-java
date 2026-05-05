const createChart = (id, label, color) => {
  const ctx = document.getElementById(id);
  return new Chart(ctx, {
    type: 'line',
    data: {
      labels: [],
      datasets: [{
        label,
        data: [],
        borderColor: color,
        tension: 0.3
      }]
    },
    options: {
      scales: { y: { min: 0, max: 100 } }
    }
  });
};

const charts = {
  cpu: createChart('cpuChart', 'CPU (%)', '#f44336'),
  memory: createChart('memoryChart', 'Memória (%)', '#2196f3'),
  network: createChart('networkChart', 'Rede (%)', '#4caf50')
};

const updateChart = (chart, value) => {
  const now = new Date().toLocaleTimeString();
  chart.data.labels.push(now);
  chart.data.datasets[0].data.push(value);
  if (chart.data.labels.length > 15) {
    chart.data.labels.shift();
    chart.data.datasets[0].data.shift();
  }
  chart.update();
};

const fetchMetrics = async () => {
  const response = await fetch('/api/metrics');
  const data = await response.json();
  updateChart(charts.cpu, data.cpu);
  updateChart(charts.memory, data.memory);
  updateChart(charts.network, data.network);
};

fetchMetrics();
setInterval(fetchMetrics, 5000);
